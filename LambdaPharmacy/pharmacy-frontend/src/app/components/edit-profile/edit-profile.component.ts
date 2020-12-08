import { Component, OnInit } from '@angular/core';
import {UserService} from "src/app/services/UserService";
import {SessionStorageService} from "src/app/services/SessionStorageService";


import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  usernameForBack: string;
  userData: any;
  id: number;
  firstName:string;
  lastName: string;
  username: string;
  password: string;
  address:string;
  email: string;
  phoneNumber:string;



  constructor(
    private userService: UserService,
    private sessionStorageService: SessionStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.usernameForBack = this.sessionStorageService.getUserName();
    console.log('Username iz patient profile: ' + this.usernameForBack);
    this.getMyUser();
  }

  editProfile() {
  this.userService.updateProfile(this.id, this.firstName, this.lastName, this.usernameForBack, this.password,
     this.email, this.address, this.phoneNumber)
    .subscribe((user:any)=>
      {
          this.router.navigate(['/patient-profile']);
      });
  }

  getMyUser() {
    this.userService.getMyUser(this.usernameForBack).subscribe( response => {
      console.log(response);
      this.userData = Object.assign([], response);
      this.firstName = this.userData.firstName;
      this.username= this.userData.username;
      this.lastName = this.userData.lastName;
      this.password= this.userData.password;
      this.address = this.userData.address;
      this.email = this.userData.email;
      this.phoneNumber = this.userData.phoneNumber;
      this.id = this.userData.id;





    });
  }
}
