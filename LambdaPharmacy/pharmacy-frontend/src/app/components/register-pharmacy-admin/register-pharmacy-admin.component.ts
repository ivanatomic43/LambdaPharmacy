import { RegistrationParams } from './../../model/registrationParams';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { AdministratorParams } from 'src/app/model/AdministratorParams';
import { UserService } from 'src/app/services/UserService';
import { Router } from '@angular/router';
import { PharmacyService } from 'src/app/services/PharmacyService';

@Component({
  selector: 'app-register-pharmacy-admin',
  templateUrl: './register-pharmacy-admin.component.html',
  styleUrls: ['./register-pharmacy-admin.component.css']
})
export class RegisterPharmacyAdminComponent implements OnInit {

  fetchedPharmacies: PharmacyDTO[] = [];
  pharmAdminForm : FormGroup;
  adminParams : AdministratorParams;

  constructor(
    private router: Router,
    private userService: UserService,
    private pharmacyService: PharmacyService,
    private formBuilder : FormBuilder
  ) { }

  ngOnInit() {

    this.pharmacyService.allPharmacies().subscribe(response => {
      this.fetchedPharmacies= response;

    });

    this.pharmAdminForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      pharmacy: ['', Validators.required]
    });

  }

  registerPharmacyAdministrator(){

    this.adminParams = new AdministratorParams(
      this.pharmAdminForm.get('firstName').value,
      this.pharmAdminForm.get('lastName').value,
      this.pharmAdminForm.get('username').value,
      this.pharmAdminForm.get('email').value,
      this.pharmAdminForm.get('password').value,
      this.pharmAdminForm.get('address').value,
      this.pharmAdminForm.get('phoneNumber').value,
      this.pharmAdminForm.get('pharmacy').value,
    );

    this.userService.registerPharmacyAdministrator(this.adminParams).subscribe( response => {
        alert("Pharmacy administrator registred!");
        this.router.navigate(['/home']);
    }, error => {
      console.log("error");
    });

  }

}
