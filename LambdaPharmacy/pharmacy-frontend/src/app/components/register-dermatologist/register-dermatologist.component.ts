import { DermatologistService } from './../../services/DermatologistService';
import { HttpClient } from '@angular/common/http';
import { RegistrationParams } from './../../model/registrationParams';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-dermatologist',
  templateUrl: './register-dermatologist.component.html',
  styleUrls: ['./register-dermatologist.component.css']
})
export class RegisterDermatologistComponent implements OnInit {

  dermatologistForm : FormGroup;
  dermatologistParams: RegistrationParams;



  constructor(
  private http: HttpClient,
  private formBuilder: FormBuilder,
  private router: Router,
  private  dermatologistService: DermatologistService
  ) { }

  ngOnInit() {

    this.dermatologistForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', Validators.required],
    });

  }


  registerDermatologist(){

    this.dermatologistParams = new RegistrationParams(
      this.dermatologistForm.get('firstName').value,
      this.dermatologistForm.get('lastName').value,
      this.dermatologistForm.get('username').value,
      this.dermatologistForm.get('email').value,
      this.dermatologistForm.get('password').value,
      this.dermatologistForm.get('address').value,
      this.dermatologistForm.get('phoneNumber').value
    );

    this.dermatologistService.registerDermatologist(this.dermatologistParams).subscribe(
      response => {
        alert("Dermatologist registred!");
        this.router.navigate(['/manage-dermatologist']);
      }
    );

  }
}
