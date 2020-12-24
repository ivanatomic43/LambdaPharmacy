import { NewPharmacist } from './../../model/NewPharmacist';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddingStaffDTO } from 'src/app/model/AddingStaffDTO';
import { UserProfileDTO } from 'src/app/model/UserProfileDTO';
import { PharmacistService } from 'src/app/services/PharmacistService';

@Component({
  selector: 'app-add-pharm',
  templateUrl: './add-pharm.component.html',
  styleUrls: ['./add-pharm.component.css']
})
export class AddPharmComponent implements OnInit {


  pharmacyID : number;
  fetchedPharmacists: UserProfileDTO[] = [];
  addingPharmForm: FormGroup;
  pharmData : NewPharmacist;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private pharmacistService : PharmacistService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

        this.pharmacyID = this.route.snapshot.params.id;

        this.addingPharmForm = new FormGroup({
          firstName : new FormControl('', [Validators.required]),
          lastName: new FormControl('', [Validators.required]),
          username: new FormControl('', [Validators.required]),
          email: new FormControl('', [Validators.required]),
          password: new FormControl('', [Validators.required]),
          address: new FormControl('', [Validators.required]),
          phoneNumber: new FormControl('', [Validators.required]),
          workFrom: new FormControl('', [Validators.required]),
          workTo: new FormControl('', [Validators.required])
        });


      }

  addPharmacist(){

    //this.dermData = this.addingDermForm.getRawValue();



    this.pharmData = new NewPharmacist(
      this.addingPharmForm.get('firstName').value,
      this.addingPharmForm.get('lastName').value,
      this.addingPharmForm.get('username').value,
      this.addingPharmForm.get('email').value,
      this.addingPharmForm.get('password').value,
      this.addingPharmForm.get('address').value,
      this.addingPharmForm.get('phoneNumber').value,
      this.addingPharmForm.get('workFrom').value,
      this.addingPharmForm.get('workTo').value
    );

    this.pharmacistService.addPharmacist(this.pharmData, this.pharmacyID).subscribe(response => {
            alert("Pharmacist added to pharmacy!");
            this.router.navigate(['/pharmacy-details/' + this.pharmacyID]);
    });


  }

}
