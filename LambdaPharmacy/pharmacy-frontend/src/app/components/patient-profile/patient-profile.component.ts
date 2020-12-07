import { UserProfileDTO } from './../../model/UserProfileDTO';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { ShareService } from 'src/app/services/ShareService';
import { UserService } from 'src/app/services/UserService';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {

  usernameForm : string;
  userData : UserProfileDTO;

  constructor(
    private  userService: UserService,
    private shareService: ShareService,
    private sessionStorageService: SessionStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.usernameForm = this.sessionStorageService.getUserName();
    console.log('Username iz patient profile: ' + this.usernameForm);
    this.getMyUser();
  }

  getMyUser() {
    this.userService.getMyUser(this.usernameForm).subscribe( response => {
      console.log(response);
      this.userData = response;


    });
  }

  editProfile() {
    this.router.navigate(['/editProfile']);
  }
}
