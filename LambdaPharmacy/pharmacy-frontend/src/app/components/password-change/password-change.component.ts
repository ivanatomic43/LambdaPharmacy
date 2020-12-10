import { UserDTO } from './../../model/UserDTO';
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { Router } from '@angular/router';
import { AuthService } from './../../services/AuthService';
import { PasswordDTO } from './../../model/PasswordDTO';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-password-change',
  templateUrl: './password-change.component.html',
  styleUrls: ['./password-change.component.css']
})
export class PasswordChangeComponent implements OnInit {

  passwordForm : FormGroup;
  passwordParams : PasswordDTO;
  private profil : UserDTO;

  constructor(
    private authService : AuthService,
    private router: Router,
    private formBuilder : FormBuilder,
    private sessionStorageService: SessionStorageService,

  ) { }

  ngOnInit() {

    this.passwordForm = this.formBuilder.group({
        oldPassword :['', Validators.required],
        newPassword : ['', Validators.required],
        conPassword: ['', Validators.required]

    });
  }

  changePassword(){

    if (!(this.passwordForm.get('newPassword').value === this.passwordForm.get('conPassword').value)) {

      alert('Passwords do not match!');
      return;
    }

    this.passwordParams = new PasswordDTO(this.passwordForm.get('oldPassword').value, this.passwordForm.get('newPassword').value);

    this.authService.changePassword(this.passwordParams).subscribe(
      (user:any) => {


        alert('Password changed!');

        this.router.navigate(['/patient-profile']);
      },
      error => {
        console.log('Password change error....');
      }
    );
  }

}
