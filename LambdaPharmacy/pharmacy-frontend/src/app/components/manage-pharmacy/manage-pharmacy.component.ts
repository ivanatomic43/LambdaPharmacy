
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
  loadedImages: Map<number, string> = new Map<number, string>();

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
        this.getImages(this.fetchedPharmacies);

      },
      err => {

      }
    );

    this.pharmacyService.refreshPharmacies.subscribe(refreshPharmacies => {
      this.fetchedPharmacies = refreshPharmacies;
    });
  }

  getImages(pharmacies : Array<PharmacyDTO>){
    var image:string = "";
    var images_list : Array<string>= [];
    for (let pharmacy of pharmacies){
       this.pharmacyService.images(pharmacy.id).subscribe(data => {

          image = data;
          this.loadedImages.set(pharmacy.id, image);
       });
    }
  }

  getImageForPharmacy(phId : number){
    return this.loadedImages.get(phId);
  }

  showPharmacyForm(){
    this.router.navigate(['/new-pharmacy']);
  }

  showPharmacyDetails(id:number){

    this.router.navigate(['/pharmacy-details/' + id]);
  }

}
