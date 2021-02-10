import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicineService } from 'src/app/services/medicineService';
import { AuthService } from 'src/app/services/AuthService';
import { UserDTO } from 'src/app/model/UserDTO';

@Component({
  selector: 'app-medicine-details',
  templateUrl: './medicine-details.component.html',
  styleUrls: ['./medicine-details.component.css']
})
export class MedicineDetailsComponent implements OnInit {

  medicineID: number;
  pharmacyID: number;
  fetchedMedicine: MedicinePreview;
  showData =  true;
  isPharmacyAdmin = false;
  isSysAdmin = false;
  profil : UserDTO;


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private medicineService: MedicineService,
    private authService : AuthService
  ) { }

  ngOnInit() {

    this.medicineID = this.route.snapshot.params.id;
    this.pharmacyID = this.route.snapshot.params.pid;

    if(this.pharmacyID == null){
      this.showData = false;
    }

    this.medicineService.getMedicineDetails(this.medicineID).subscribe(response => {
      this.fetchedMedicine = response;
    });

    this.authService.getLogged().subscribe( response => {
      this.profil = response;
      const role = this.profil.authorities[0];

      if(role == 'ROLE_PHARMACY_ADMIN'){
        this.isPharmacyAdmin = true;
        //this.anyLogged = true;
      }
      else {
        this.isPharmacyAdmin = false;
      }
      if(role =='ROLE_SYS_ADMIN'){
        this.isSysAdmin = true;

      } else {
        this.isSysAdmin = false;
      }

  });


  }

  removeMedicine(id:number, pharmID: number){

    this.medicineService.removeMedicine(id, pharmID).subscribe(response =>{
      alert("Medicine removed from pharmacy!");
      this.router.navigate(['/pharmacy-medicines/' + pharmID]);
    }, error => {
      if(error.status == 404){
        alert("Medicine not found!");
      }
      if(error.status ==400){
        alert("Reserved medicine can not be deleted!");
      }
    });

  }

  editMedicine(id:number, pharmID: number){

    this.router.navigate(['/edit-medicine/'+ id +'/' + pharmID]);
  }
  backToList(){
    this.router.navigate(['/pharmacy-medicines/'+ this.pharmacyID]);
  }

  backToListAdmin(){
    this.router.navigate(['/list-of-medicines']);
  }

}
