import { RegistrationParams } from './../../model/registrationParams';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/UserService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-sys-admin',
  templateUrl: './register-sys-admin.component.html',
  styleUrls: ['./register-sys-admin.component.css']
})
export class RegisterSysAdminComponent implements OnInit {

  adminForm: FormGroup;
  adminParams : RegistrationParams;

  constructor(
    private formBuilder :FormBuilder,
    private userService : UserService,
    private router : Router
  ) { }

  ngOnInit() {

    this.adminForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', Validators.required],
    })



  }

  registerSysAdmin(){

    this.adminParams = new RegistrationParams(
      this.adminForm.get('firstName').value,
      this.adminForm.get('lastName').value,
      this.adminForm.get('username').value,
      this.adminForm.get('email').value,
      this.adminForm.get('password').value,
      this.adminForm.get('address').value,
      this.adminForm.get('phoneNumber').value
    );

    this.userService.registerSysAdmin(this.adminParams).subscribe(
      response => {
        alert("System administrator registred!");
        this.router.navigate(['/home']);
      }
    );


  }

}
