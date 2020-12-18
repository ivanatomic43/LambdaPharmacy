import { Component, OnInit } from '@angular/core';
import {PharmacyService} from '../../services/PharmacyService';
import {PharmacyDTO} from '../../model/PharmacyDTO';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-of-pharmacies',
  templateUrl: './list-of-pharmacies.component.html',
  styleUrls: ['./list-of-pharmacies.component.css']
})
export class ListOfPharmaciesComponent implements OnInit {
  loaded = false;
  fetchedPharmacies: PharmacyDTO[] = [];
  constructor(
    private pharmacyService: PharmacyService,
    private router: Router
  ) { }

  ngOnInit(){
    this.pharmacyService.allPharmacies().subscribe(
      resp => {
        this.fetchedPharmacies = resp;
        console.log(this.fetchedPharmacies);
        this.loaded = true;

      },
      err => {

      }
    );

    this.pharmacyService.refreshPharmacies.subscribe(refreshPharmacies => {
      this.fetchedPharmacies = refreshPharmacies;
    });


  }

  showPharmacyDet(id:number){
    this.router.navigate(['/pharmacy-details/' + id]);
  }

}
