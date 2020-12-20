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
  profil: UserDTO;

  //dermatologists
  dataSource: MatTableDataSource<UserProfileDTO>;
  loadedDerm = false;
  displayedColumns: string[]= [
    'id',
    'firstName',
    'lastName',
    'username',
    'email',
    'address',
    'phoneNumber',
    'role',
    'action'
  ];

  constructor(
    private route: ActivatedRoute,
    private pharmacyService: PharmacyService,
    private router: Router,
    private authService: AuthService
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
      });

  }

  registerDermForm(){
    this.router.navigate(['/register-dermatologist']);
  }

}
