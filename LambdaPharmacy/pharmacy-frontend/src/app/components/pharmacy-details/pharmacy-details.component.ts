import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UserProfileDTO } from 'src/app/model/UserProfileDTO';
import { MatTableDataSource } from '@angular/material/table';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { Component, OnInit } from '@angular/core';
import { PharmacyService } from 'src/app/services/PharmacyService';

@Component({
  selector: 'app-pharmacy-details',
  templateUrl: './pharmacy-details.component.html',
  styleUrls: ['./pharmacy-details.component.css']
})
export class PharmacyDetailsComponent implements OnInit {

  pharmacyID: number;
  fetchedPharmacy: PharmacyDTO;
  loaded = false;

  //dermatologists
  dataSource: MatTableDataSource<UserProfileDTO>;
  loadedDerm = false;
  displayedColumns: string[]= [
    'id',
    'firstName',
    'lastName',
    'username',
    'email',
    'address',
    'phoneNumber',
    'role'
  ];

  constructor(
    private route: ActivatedRoute,
    private pharmacyService: PharmacyService,
    private router: Router
  ) { }

  ngOnInit() {

      this.pharmacyID = this.route.snapshot.params.id;

      this.pharmacyService.getPharmacyById(this.pharmacyID).subscribe(response=>{
        this.fetchedPharmacy = response;
        this.loaded= true;
      });

  }

}
