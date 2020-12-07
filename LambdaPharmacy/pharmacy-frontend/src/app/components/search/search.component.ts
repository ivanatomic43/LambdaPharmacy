import { AlertService } from './../../services/AlertService';
import { MedicineService } from './../../services/medicineService';
import { MedicineSearch } from './../../model/MedicineSearch';
import { PharmacyService } from './../../services/PharmacyService';
import { SearchService } from 'src/app/services/SearchService';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { SimpleSearch } from 'src/app/model/SimpleSearch';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  medicineShow = false;
  pharmacyShow= true;

  private searchPharmacyParams : SimpleSearch;
  private searchMedicineParams : MedicineSearch;
  private nameMedicine : string;

  searchPharmacyForm : FormGroup;
  searchMedicineForm : FormGroup;

  pharmacyName: string;
  pharmacyLocation : string;
  pharmacyRating: number;

  constructor(
    private searchService : SearchService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route : ActivatedRoute,
    private pharmacyService : PharmacyService,
    private medicineService : MedicineService,
    private alertService : AlertService
  ) { }

  ngOnInit() {

  this.searchPharmacyForm = this.formBuilder.group({
    'pharmacyName' : [''],
    'pharmacyLocation': [''],
    'pharmacyRating': ['']
  });

  this.searchMedicineForm = this.formBuilder.group({
    'medicineName' : ['', Validators.required]
  });


  }

  showMedicineSearch() {

    if (!this.medicineShow) {
      this.medicineShow = true;
      this.pharmacyShow = false;
    } else{
      this.medicineShow= false;
      this.pharmacyShow= true;
    }
  }

  showPharmacySearch(){
    if(this.pharmacyShow){
      this.medicineShow= true;
      this.pharmacyShow = false;
    } else {
      this.medicineShow= false;
      this.pharmacyShow= true;
    }
  }

  searchPharmacy(){
    this.alertService.clear();

    this.searchPharmacyParams = new SimpleSearch(this.searchPharmacyForm.get('pharmacyName').value, this.searchPharmacyForm.get('pharmacyLocation').value,
    );

      const searchParams = new SimpleSearch(
        this.pharmacyName,
        this.pharmacyLocation

      );

      console.log(this.searchPharmacyParams);

    this.searchService.searchPharmacy(this.searchPharmacyParams).subscribe(
        response => {
          this.pharmacyService.refreshPharmacies.next(response);
        },
        error => {
               this.alertService.error("No search results.");
        }
    );
  }

  searchMedicine(){
    this.alertService.clear();

      //this.searchMedicineParams = new MedicineSearch(this.searchMedicineForm.get('medicineName').value);
      this.nameMedicine = this.searchMedicineForm.get('medicineName').value;


      this.searchService.searchMedicine(this.nameMedicine).subscribe(
        response => {
          this.medicineService.refreshMedicines.next(response);
        },
        error =>{
            this.alertService.error("No search results.");
        }
      );


  }
}
