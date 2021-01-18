import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { PharmacyService } from 'src/app/services/PharmacyService';

@Component({
  selector: 'app-my-pharmacy',
  templateUrl: './my-pharmacy.component.html',
  styleUrls: ['./my-pharmacy.component.css']
})
export class MyPharmacyComponent implements OnInit {

  pharmacy : PharmacyDTO;

  constructor(
    private router: Router,
    private pharmacyService : PharmacyService
  ) { }

  ngOnInit() {

        this.pharmacyService.getAdminsPharmacy().subscribe(response => {
          this.pharmacy = response;

        });

  }

  showPharmacyDet(id:number){
    this.router.navigate(['/pharmacy-details/' + id])
  }

}
