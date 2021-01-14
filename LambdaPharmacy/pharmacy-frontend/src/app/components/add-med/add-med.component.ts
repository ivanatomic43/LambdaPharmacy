import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { MedicineService } from 'src/app/services/medicineService';

@Component({
  selector: 'app-add-med',
  templateUrl: './add-med.component.html',
  styleUrls: ['./add-med.component.css']
})
export class AddMedComponent implements OnInit {

  fetchedMedicines: MedicinePreview[] = [];
  pharmacyID: number;
  medicineID: number;
  addingMedForm: FormGroup;


  constructor(
    private router: Router,
    private medicineService: MedicineService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {

      this.pharmacyID = this.route.snapshot.params.id;

      this.medicineService.getMedForAdd(this.pharmacyID).subscribe( response => {
        this.fetchedMedicines = response;

      });

      this.addingMedForm = new FormGroup({
        medicine: new FormControl('', [Validators.required])
      });

  }

  addMedicine(){

    this.medicineID = this.addingMedForm.get('medicine').value;



    this.medicineService.addMedicineToPharmacy(this.pharmacyID, this.medicineID).subscribe(response => {
      alert("Medicine added to pharmacy!");
      this.router.navigate(['/pharmacy-medicines/' + this.pharmacyID]);
    }, error => {
      alert("error...");
    });


  }

}
