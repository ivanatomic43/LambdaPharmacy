import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicineService } from 'src/app/services/medicineService';
import { AuthService } from 'src/app/services/AuthService';
import { UserDTO } from 'src/app/model/UserDTO';

@Component({
  selector: 'app-medicine-details',
  templateUrl: './medicine-details.component.html',
  styleUrls: ['./medicine-details.component.css']
})
export class MedicineDetailsComponent implements OnInit {

  medicineID: number;
  pharmacyID: number;
  fetchedMedicine: MedicinePreview;
  showData =  true;
  isPharmacyAdmin = false;
  profil : UserDTO;


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private medicineService: MedicineService,
    private authService : AuthService
  ) { }

  ngOnInit() {

    this.medicineID = this.route.snapshot.params.id;
    this.pharmacyID = this.route.snapshot.params.pid;

    if(this.pharmacyID == null){
      this.showData = false;
    }

    this.medicineService.getMedicineDetails(this.medicineID).subscribe(response => {
      this.fetchedMedicine = response;
    });

    this.authService.getLogged().subscribe( response => {
      this.profil = response;
      const role = this.profil.authorities[0];

      if(role == 'ROLE_PHARMACY_ADMIN'){
        this.isPharmacyAdmin = true;
        //this.anyLogged = true;
      }
      else {
        this.isPharmacyAdmin = false;
      }

  });


  }

}
