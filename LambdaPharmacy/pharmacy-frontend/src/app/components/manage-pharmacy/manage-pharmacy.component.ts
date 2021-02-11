
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-manage-pharmacy',
  templateUrl: './manage-pharmacy.component.html',
  styleUrls: ['./manage-pharmacy.component.css']
})
export class ManagePharmacyComponent implements OnInit {

  loaded = false;
  fetchedPharmacies: PharmacyDTO[] = [];


  constructor(
    private pharmacyService: PharmacyService,
    private router: Router,
    private myDialog : MatDialog

  ) { }

  ngOnInit() {

    this.pharmacyService.allPharmacies().subscribe(
      resp => {
        this.fetchedPharmacies = resp;

        this.loaded = true;


      },
      err => {

      }
    );

    this.pharmacyService.refreshPharmacies.subscribe(refreshPharmacies => {
      this.fetchedPharmacies = refreshPharmacies;
    });
  }




  showPharmacyForm(){
    this.router.navigate(['/new-pharmacy']);
  }

  showPharmacyDetails(id:number){

    this.router.navigate(['/pharmacy-details/' + id]);
  }

}
