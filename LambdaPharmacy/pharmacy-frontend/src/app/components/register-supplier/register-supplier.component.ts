import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationParams } from 'src/app/model/registrationParams';
import { SysAdminService } from 'src/app/services/SysAdminService';
import { UserService } from 'src/app/services/UserService';

@Component({
  selector: 'app-register-supplier',
  templateUrl: './register-supplier.component.html',
  styleUrls: ['./register-supplier.component.css']
})
export class RegisterSupplierComponent implements OnInit {

  supplierForm: FormGroup;
  supplierParams : RegistrationParams;

  constructor(
    private formBuilder : FormBuilder,
    private userService : UserService,
    private router: Router
  ) { }

  ngOnInit() {

    this.supplierForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phoneNumber: ['', Validators.required],
    })
  }

  registerSupplier(){

    this.supplierParams = new RegistrationParams(
      this.supplierForm.get('firstName').value,
      this.supplierForm.get('lastName').value,
      this.supplierForm.get('username').value,
      this.supplierForm.get('email').value,
      this.supplierForm.get('password').value,
      this.supplierForm.get('address').value,
      this.supplierForm.get('phoneNumber').value
    );

    this.userService.registerSupplier(this.supplierParams).subscribe(
      response => {
        alert("Supplier registred!");
        this.router.navigate(['/home']);
      }
    );
  }

}
