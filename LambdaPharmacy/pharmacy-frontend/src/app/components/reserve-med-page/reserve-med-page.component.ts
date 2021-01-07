import { Subscription } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ReservationParams } from './../../model/ReservationParams';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicineService } from 'src/app/services/medicineService';

@Component({
  selector: 'app-reserve-med-page',
  templateUrl: './reserve-med-page.component.html',
  styleUrls: ['./reserve-med-page.component.css']
})
export class ReserveMedPageComponent implements OnInit {

  medicineID:number;
  pharmacyID:number;
  reservationParams: ReservationParams;
  reserveForm : FormGroup;


  constructor(
    private router :Router,
    private route : ActivatedRoute,
    private medicineService: MedicineService
  ) { }

  ngOnInit() {

    this.medicineID= this.route.snapshot.params.mid;
    this.pharmacyID = this.route.snapshot.params.pid;

    this.reserveForm = new FormGroup({
      date: new FormControl('', [Validators.required])
    });

  }


  reserve(){

      this.reservationParams = new ReservationParams(
        this.medicineID,
        this.pharmacyID,
        this.reserveForm.get('date').value
      );

      this.medicineService.reserveMedicine(this.reservationParams).subscribe( response => {
          alert("Medicine reserved!");
          this.router.navigate(['/home']);
      }, error => {
        console.log('error..');
      });

  }
}
