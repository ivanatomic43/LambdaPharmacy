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
      console.log('ng on init navabr');
      this.profil = data;
      const role = this.profil.authorities[0];
      console.log(role);
      if (role == 'ROLE_PATIENT') {
        console.log('usao u role patient navbar');
        this.isPatient  = true;
      } else {
        this.isPatient = false;
      }
      if (role == 'ROLE_PHARMACY_ADMIN') {
        this.isPharmacyAdmin = true;
      } else {
        this.isPharmacyAdmin = false;
      }
    });
    console.log('dosao dovde');
    this.getLoggedUser();
  }

  logout() {
    this.authService.logout().subscribe(data => {

      },
      error => {
        alert('greska');
      });
    this.sessionStorageService.signOut();
    this.anyLogged = false;
    this.isPatient = false;
    this.router.navigate(['/login']);
  }


    getLoggedUser() {
        console.log('usao u getloggedUser navbar');
        this.authService.getLogged().subscribe(data => {
              console.log('usao u this');
          this.profil = data;
          this.anyLogged = true;
          this.shareService.sendIsLogged(this.anyLogged);
          this.shareService.sendProfile(this.profil);
          const role = this.profil.authorities[0];
          console.log('Uloga u get logged navbar:' + role);
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
        },
        error => {

          this.profil = null;
          this.anyLogged = false;
          this.isPatient = false;
          this.isPharmacyAdmin = false;
        });

  }

}
