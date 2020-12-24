import { AddingDermDTO } from './../../model/AddingDermDTO';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { UserProfileDTO } from './../../model/UserProfileDTO';
import { DermatologistService } from './../../services/DermatologistService';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-add-derm',
  templateUrl: './add-derm.component.html',
  styleUrls: ['./add-derm.component.css']
})
export class AddDermComponent implements OnInit {

  pharmacyID : number;
  fetchedDermatologist: UserProfileDTO[] = [];
  addingDermForm: FormGroup;
  dermData : AddingDermDTO;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private dermatologistService : DermatologistService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

        this.pharmacyID = this.route.snapshot.params.id;
        this.dermatologistService.getAllDermatologists().subscribe(response => {
          this.fetchedDermatologist= response;

        });

        this.addingDermForm = new FormGroup({
          dermatologist: new FormControl('', [Validators.required]),
          dateFrom: new FormControl('', [Validators.required]),
          dateTo: new FormControl('', [Validators.required]),
          workFrom: new FormControl('', [Validators.required]),
          workTo: new FormControl('', [Validators.required])
        });


      }


  addDermatologist(){

    //this.dermData = this.addingDermForm.getRawValue();



    this.dermData = new AddingDermDTO(
      this.addingDermForm.get('dermatologist').value,
      this.addingDermForm.get('dateFrom').value,
      this.addingDermForm.get('dateTo').value,
      this.addingDermForm.get('workFrom').value,
      this.addingDermForm.get('workTo').value
    );
    console.log("DERM ID:" + this.dermData.id);
    this.dermatologistService.addDermatologist(this.dermData, this.pharmacyID).subscribe(response => {
            alert("Dermatologist added to pharmacy!");
            this.router.navigate(['/pharmacy-details/' + this.pharmacyID]);
    });


  }
}
