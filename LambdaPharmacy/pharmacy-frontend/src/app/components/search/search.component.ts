import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  medicineShow = false;
  pharmacyShow= true;

  constructor() { }

  ngOnInit() {
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
}
