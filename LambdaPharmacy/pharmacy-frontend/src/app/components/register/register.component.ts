import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../services/AuthService';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RegistrationParams} from '../../model/registrationParams';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationForm: FormGroup;
  registrationParams: RegistrationParams;
  submitted = false;
  success = false;
  isEqual = true;
  isSignedUp = false;

  constructor( private http: HttpClient, private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {

    this.registrationForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', Validators.required],

      confirmPassword: ['', Validators.required],




    });
  }

  registerPatient() {
    this.submitted = true;


    if (this.registrationForm.invalid) {
      return;
    }

    if (!(this.registrationForm.get('password').value === this.registrationForm.get('confirmPassword').value)) {
      this.isEqual = false;
      alert('Passwords do not match!');
      return;
    }

    this.success = true;
    console.log('muuuuu');
    this.registrationParams = new RegistrationParams(this.registrationForm.get('firstName').value, this.registrationForm.get('lastName').value, this.registrationForm.get('username').value,
      this.registrationForm.get('email').value, this.registrationForm.get('password').value, this.registrationForm.get('address').value,
      this.registrationForm.get('phoneNumber').value);


    this.authService.registerPatient(this.registrationParams).subscribe(
      data => {
        alert('Registration request sent! Check e-mail to confirm your registration!');
        this.router.navigate(['/login']);

      },
      error => {
        console.log(error);
        this.isSignedUp = false;
      }

    );





  }


}
