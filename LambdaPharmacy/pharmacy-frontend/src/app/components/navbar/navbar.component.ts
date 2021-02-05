import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/UserService';
import {AuthService} from '../../services/AuthService';
import {ShareService} from '../../services/ShareService';
import {Router} from '@angular/router';
import {SessionStorageService} from '../../services/SessionStorageService';
import {UserDTO} from '../../model/UserDTO';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  anyLogged = false;
  isPatient = false;
  isDermatologist = false;
  isPharmacist = false;
  isPharmacyAdmin = false;
  isSupplier = false;
  isSysAdmin = false;

  profil: UserDTO;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private sessionStorageService: SessionStorageService,
    private shareService: ShareService

  ) {
    console.log('navbar constructor');
    this.getLoggedUser();
  }

  ngOnInit() {
    this.shareService.shareIsLogged.subscribe(data => {
      this.anyLogged = data;
    });

    this.shareService.shareProfile.subscribe(data => {

      this.profil = data;
      const role = this.profil.authorities[0];
      console.log(role);
      if (role == 'ROLE_PATIENT') {

        this.isPatient  = true;
      } else {
        this.isPatient = false;
      }
      if (role == 'ROLE_PHARMACY_ADMIN') {
        this.isPharmacyAdmin = true;
      } else {
        this.isPharmacyAdmin = false;
      }
      if(role == 'ROLE_SYS_ADMIN') {

        this.isSysAdmin = true;
      } else {
        this.isSysAdmin = false;
      }
      if(role =="ROLE_SUPPLIER"){
        this.isSupplier = true;
      } else {
        this.isSupplier = false;
      }
    });

    this.getLoggedUser();
  }

  logout() {
    this.authService.logout().subscribe(data => {

      },
      error => {
        alert('No one is logged');
      });
    this.sessionStorageService.signOut();
    this.anyLogged = false;
    this.isPatient = false;
    this.isPharmacyAdmin = false;
    this.isSysAdmin = false;
    this.isSupplier = false;
    this.router.navigate(['/login']);
  }


    getLoggedUser() {

        this.authService.getLogged().subscribe(data => {

          this.profil = data;
          this.anyLogged = true;
          this.shareService.sendIsLogged(this.anyLogged);
          this.shareService.sendProfile(this.profil);
          const role = this.profil.authorities[0];

          if (role == 'ROLE_PHARMACY_ADMIN') {
            this.isPharmacyAdmin = true;
          } else {
            this.isPharmacyAdmin = false;
          }
            // tslint:disable-next-line:triple-equals
          if(role == 'ROLE_PATIENT') {

            this.isPatient = true;
          } else {
            this.isPatient = false;
          }
          if(role == 'ROLE_SYS_ADMIN') {

            this.isSysAdmin = true;
          } else {
            this.isSysAdmin = false;
          }
          if(role =="ROLE_SUPPLIER"){
            this.isSupplier = true;
          } else {
            this.isSupplier = false;
          }
        },
        error => {

          this.profil = null;
          this.anyLogged = false;
          this.isPatient = false;
          this.isPharmacyAdmin = false;
          this.isSupplier= false;
        });

  }

}
