import { AppointmentService } from './../../services/AppointmentService';

import { DermatologistService } from './../../services/DermatologistService';
import { DermDTO } from './../../model/DermDTO';
import { UserDTO } from './../../model/UserDTO';
import { AuthService } from './../../services/AuthService';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserProfileDTO } from 'src/app/model/UserProfileDTO';
import { MatTableDataSource } from '@angular/material/table';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { Component, OnInit } from '@angular/core';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { PharmacistService } from 'src/app/services/PharmacistService';
import { AppointmentPreview } from './../../model/AppointmentPreview';
import { UserService } from 'src/app/services/UserService';

@Component({
  selector: 'app-pharmacy-details',
  templateUrl: './pharmacy-details.component.html',
  styleUrls: ['./pharmacy-details.component.css']
})
export class PharmacyDetailsComponent implements OnInit {

  pharmacyID: number;
  fetchedPharmacy: PharmacyDTO;
  loaded = false;
  isSysAdmin = false;
  isPharmacyAdmin = false;
  isPatient = false;
  profil: UserDTO;
  appointmentID:number;

  admins : UserProfileDTO[] = [];
  isSubscribed = false;


  //dermatologists
  dataSource: MatTableDataSource<DermDTO>;
  loadedDerm = false;
  dermatologists: DermDTO[] = [];
  displayedColumns: string[]= [
    'id',
    'firstName',
    'lastName',
    'workFrom',
    'workTo',
    'action'
  ];

  //pharmacists
  dataSource1: MatTableDataSource<DermDTO>;
  loadedPharm = false;
  pharmacists: DermDTO[]= [];
  displayedColumns1: string[]=[
    'id',
    'firstName',
    'lastName',
    'workFrom',
    'workTo',
    'action'
  ];

 //predefined appointments
  dataSource2 : MatTableDataSource<AppointmentPreview>;
  appointments: AppointmentPreview[] = [];
  displayedColumns2: string[]= [
    'id',
    'dateOfAppointment',
    'meetingTime',
    'dermatologist',
    'duration',
    'price',
    'action'
  ];

  constructor(
    private route: ActivatedRoute,
    private pharmacyService: PharmacyService,
    private router: Router,
    private authService: AuthService,
    private dermatologistService: DermatologistService,
    private pharmacistService: PharmacistService,
    private appointmentService :AppointmentService,
    private userService: UserService
  ) { }

  ngOnInit() {

      this.pharmacyID = this.route.snapshot.params.id;

      this.pharmacyService.getPharmacyById(this.pharmacyID).subscribe(response=>{
        this.fetchedPharmacy = response;
        this.loaded= true;
      });

      this.userService.getAdminsForPharmacy(this.pharmacyID).subscribe(response => {
        this.admins = response;

      });

      this.authService.getLogged().subscribe(response => {
          this.profil= response;
          const role = this.profil.authorities[0];
          if(role == 'ROLE_SYS_ADMIN') {

            this.isSysAdmin = true;
          } else {
            this.isSysAdmin = false;
          }
          if(role == 'ROLE_PHARMACY_ADMIN'){
            this.isPharmacyAdmin = true;
          } else {
            this.isPharmacyAdmin = false;
          }
          if(role == 'ROLE_PATIENT'){
            this.isPatient = true;
            //checking subscription

            this.userService.checkIfSub(this.pharmacyID).subscribe(response =>{
              this.isSubscribed = response;

            });

          } else {
            this.isPatient = false;
          }
      });

      this.fetchDermatologists();
      this.fetchPharmacists();
      this.fetchAppointments();

  }

  addDermatologist(id:number){

      this.router.navigate(['/add-derm/' + id]);
  }

  fetchDermatologists(){
    this.dermatologistService.getAllDermatologistsForPharmacy(this.pharmacyID).subscribe(response => {
      this.dermatologists= response;

      this.dataSource= new MatTableDataSource(this.dermatologists);
  });

  }

  addPharmacist(id:number){
    this.router.navigate(['/add-pharm/' + id]);
  }

  fetchPharmacists(){

    this.pharmacistService.getAllPharmacistForPharmacy(this.pharmacyID).subscribe(response => {
      this.pharmacists = response;
      this.dataSource1 = new MatTableDataSource(this.pharmacists);
    });
  }


  makeAnAppointment(pid:number, did:number){

    this.router.navigate(['/make-an-app-d/' + pid + '/' + did]);

  }

  fetchAppointments(){
    this.appointmentService.getAllPredefinedAp(this.pharmacyID).subscribe(response => {
      this.appointments= response;
      this.dataSource2 = new MatTableDataSource(this.appointments);
    });
  }

  reserve(id:number){
      this.appointmentID = id;
      this.appointmentService.reserveAppointment(this.appointmentID).subscribe(response => {
         alert("Appointment reserved!");
         this.router.navigate(['my-appointments']);
      });
  }

  showMedicines(id:number){
    this.router.navigate(['pharmacy-medicines/'+ id]);
  }

  reservePharmacist(pid: number, ppid: number){

    this.router.navigate(['reserve-pharmacist/' + pid + '/' + ppid]);
  }

  removeDermatologist(pid:number, did: number){

    this.dermatologistService.removeDermatologist(pid, did).subscribe(response => {
      alert("Dermatologist removed!");
      this.fetchDermatologists();

    }, error => {
      alert("Dermatologist can not be removed because of reserved appointments!");
    });
  }

  removePharmacist(pid:number, ppid:number){
   this.pharmacistService.removePharmacist(pid, ppid).subscribe(response => {
     alert("Pharmacist removed!");
     this.fetchPharmacists();
   }, error => {
     alert("Pharmacist can not be removed because of reserved appointments!");
   });
  }

  subscribe(id:number){
    this.pharmacyService.subscribeForNewsletter(id).subscribe(response => {
      this.isSubscribed= true;
      alert("Subscribed!");
    }, error => {
      console.log("error..");
    });
  }

  showPromotions(id:number){
    this.router.navigate(['/promotions/' + id]);
  }

}
