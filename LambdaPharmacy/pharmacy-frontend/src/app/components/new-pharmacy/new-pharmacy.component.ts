import { UserProfileDTO } from 'src/app/model/UserProfileDTO';
import { Router } from '@angular/router';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { Image } from 'src/app/model/Image';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { PharmacyService } from 'src/app/services/PharmacyService';


@Component({
  selector: 'app-new-pharmacy',
  templateUrl: './new-pharmacy.component.html',
  styleUrls: ['./new-pharmacy.component.css']
})
export class NewPharmacyComponent implements OnInit {

  pharmacyForm: FormGroup;
  choosenImage: Image;
  imgSource : string = '';
  submittedData : PharmacyDTO;
  fetchedAdministrators : UserProfileDTO[] = [];


  constructor
  (
    private pharmacyService : PharmacyService,
    private router : Router
  ) { }

  ngOnInit() {

    this.pharmacyForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      pharmacyAdministrator : new FormControl('', [Validators.required])
    });

    this.pharmacyService.getAdministrators().subscribe(response => {
        this.fetchedAdministrators = response;
    }, error => {
      console.log("There is no pharmacy administrators in system...");
    })



  }

  registerPharmacy(){

    console.log("RAW VALUE:", this.pharmacyForm.getRawValue());

    this.submittedData = this.pharmacyForm.getRawValue();


    this.pharmacyService.createNewPharmacy(this.submittedData).subscribe(
      response => {

            alert("Pharmacy registred!");
            this.router.navigate(['/manage-pharmacy']);

      },
      error => {
        alert("Something is wrong...");
      }
    );



  }

}
