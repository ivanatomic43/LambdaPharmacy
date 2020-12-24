import { DermatologistService } from './../../services/DermatologistService';
import { Router } from '@angular/router';
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { UserProfileDTO } from 'src/app/model/UserProfileDTO';
import { UserDTO } from 'src/app/model/UserDTO';
import { AuthService } from 'src/app/services/AuthService';


@Component({
  selector: 'app-dermatologists',
  templateUrl: './dermatologists.component.html',
  styleUrls: ['./dermatologists.component.css']
})
export class DermatologistsComponent implements OnInit {

  dataSource: MatTableDataSource<UserProfileDTO>;
  dermatologists: UserProfileDTO[] = [];
  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'username',
    'email',
    'address',
    'phoneNumber',
    'role'
  ];

  loaded = false;
  isSysAdmin = false;
  profil: UserDTO;

  constructor(
    private router: Router,
    private sessionStorageService: SessionStorageService,
    private dermatologistService : DermatologistService,
    private authService : AuthService

  ) { }

  ngOnInit() {
    this.fetchDermatologists();

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


 fetchDermatologists(){
  this.dermatologistService.getAllDermatologists().subscribe(response => {
    this.dermatologists= response;

    this.dataSource= new MatTableDataSource(this.dermatologists);
});

 }

 registerDermForm(){
  this.router.navigate(['/register-dermatologist']);
}

}
