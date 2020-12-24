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
  profil: UserDTO;

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

  constructor(
    private route: ActivatedRoute,
    private pharmacyService: PharmacyService,
    private router: Router,
    private authService: AuthService,
    private dermatologistService: DermatologistService
  ) { }

  ngOnInit() {

      this.pharmacyID = this.route.snapshot.params.id;

      this.pharmacyService.getPharmacyById(this.pharmacyID).subscribe(response=>{
        this.fetchedPharmacy = response;
        this.loaded= true;
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
      });

      this.fetchDermatologists();

  }

  addDermatologist(id:number){
    alert("!!!!");
    alert(id);
      this.router.navigate(['/add-derm/' + id]);
  }

  fetchDermatologists(){
    this.dermatologistService.getAllDermatologistsForPharmacy(this.pharmacyID).subscribe(response => {
      this.dermatologists= response;

      this.dataSource= new MatTableDataSource(this.dermatologists);
  });

  }

}
