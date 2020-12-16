import { Router } from '@angular/router';
import { PharmacyDTO } from './../model/PharmacyDTO';
import { Image } from './../model/Image';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PharmacyService } from './../services/PharmacyService';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';


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
      file: new FormControl('', [Validators.required])
    });

  }


  onFileSelected($event: Event) {
    let reader = new FileReader();

    var file = $event.target['files'][0];
    console.log('Image is choosen');
    reader.onload = (e: any) => {
      this.imgSource = e.target.result;
      var newPicture = new Image();
      newPicture.id = 1;
      newPicture.data = file;
      this.choosenImage= newPicture;
      console.log(newPicture.data);
    };
    reader.readAsDataURL(file);
  }

  registerPharmacy(){

    console.log("RAW VALUE:", this.pharmacyForm.getRawValue());

    this.submittedData = this.pharmacyForm.getRawValue();
    console.log(this.submittedData.name);


    this.pharmacyService.createNewPharmacy(this.submittedData).subscribe(
      response => {
        let pharmacy = response as PharmacyDTO;
        console.log(pharmacy.id);
        console.log(this.choosenImage);
        this.pharmacyService.addImage(pharmacy.id, this.choosenImage).subscribe(
          response => {
            alert("Pharmacy registred!");
            this.router.navigate(['/manage-pharmacy']);
          },
          error =>{}
        );
      },
      error => {
        alert("Something is wrong...");
      }
    );



  }

}
