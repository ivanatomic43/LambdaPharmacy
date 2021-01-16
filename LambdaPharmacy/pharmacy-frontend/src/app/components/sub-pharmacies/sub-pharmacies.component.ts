import { Router } from '@angular/router';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { Component, OnInit } from '@angular/core';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';

@Component({
  selector: 'app-sub-pharmacies',
  templateUrl: './sub-pharmacies.component.html',
  styleUrls: ['./sub-pharmacies.component.css']
})
export class SubPharmaciesComponent implements OnInit {

  fetchedPharmacies : PharmacyDTO[] = [];

  constructor(
    private pharmacyService : PharmacyService,
    private router: Router
    ) { }

  ngOnInit() {

    this.pharmacyService.getSubPharmacies().subscribe(
      resp => {
        this.fetchedPharmacies = resp;


      },
      err => {

      }
    );

    this.pharmacyService.refreshPharmacies.subscribe(refreshPharmacies => {
      this.fetchedPharmacies = refreshPharmacies;
    });


  }

  showPharmacyDetails(id:number){
    this.router.navigate(['/pharmacy-details/' + id]);

  }

}
