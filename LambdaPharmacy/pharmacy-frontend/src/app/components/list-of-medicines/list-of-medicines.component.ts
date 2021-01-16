import { ReservationParams } from './../../model/ReservationParams';
import { UserDTO } from './../../model/UserDTO';
import { AuthService } from './../../services/AuthService';
import { Component, OnInit } from '@angular/core';
import {MedicineDTO} from '../../model/MedicineDTO';
import {MedicineService} from '../../services/medicineService';
import {Router} from '@angular/router';
import { MedicinePreview } from 'src/app/model/MedicinePreview';

@Component({
  selector: 'app-list-of-medicines',
  templateUrl: './list-of-medicines.component.html',
  styleUrls: ['./list-of-medicines.component.css']
})
export class ListOfMedicinesComponent implements OnInit {

  loaded = false;
  fetchedMedicines: MedicinePreview[] = [];
  isPatient = false;
  isSysAdmin = false;
  profil :UserDTO;
  reservationParams : ReservationParams;
  anyLogged = false;

  constructor(
    private medicineService: MedicineService,
    private router: Router,
    private authService: AuthService

  ) {}

  ngOnInit(){

    this.authService.getLogged().subscribe(response => {

      this.profil= response;
      const role = this.profil.authorities[0];

      if(role == 'ROLE_PATIENT'){
        this.isPatient = true;
        this.anyLogged = true;
        this.medicineService.getNotReservedMedicines().subscribe(response => {

          this.fetchedMedicines = response;
          this.loaded = true;
        });

        this.medicineService.refreshMedicines.subscribe(refreshMedicines => {
          this.fetchedMedicines = refreshMedicines;
        });

        return;
      }else {
        this.isPatient= false;
      }
      if(role == 'ROLE_SYS_ADMIN'){
        this.isSysAdmin= true;
        this.anyLogged= true;

        this.medicineService.getAllMedicinesInSystem().subscribe( response => {
          this.fetchedMedicines = response;
          console.log(this.fetchedMedicines);
          this.loaded = true;

        });
        this.medicineService.refreshMedicines.subscribe(refreshMedicines => {
          this.fetchedMedicines = refreshMedicines;
        });

         return;

      } else {
        this.isSysAdmin = false;
      }






      }, error => {
        console.log("There is no logged user...");
        this.medicineService.allMedicines().subscribe(
          resp => {
            this.fetchedMedicines = resp;
            console.log(this.fetchedMedicines);
            this.loaded = true;

          },
          err => {

          }
        );

        this.medicineService.refreshMedicines.subscribe(refreshMedicines => {
          this.fetchedMedicines = refreshMedicines;
        });
      });




    }


    reserveMedicine(mid:number, pid:number){

      this.router.navigate(['/reserve-med-page/'+ mid + '/' + pid]);

    }

    showMedicineForm(){
      this.router.navigate(['/register-medicine']);
    }

    showMedicineDetails(id:number, pid:number){
      if(pid == null){
        this.router.navigate(['/medicine-details/' + id]);
      } else {
        this.router.navigate(['/medicine-details/'+ id +'/' + pid]);
      }

    }
}
