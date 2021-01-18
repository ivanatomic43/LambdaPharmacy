import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';
import { PromotionDTO } from './../../model/PromotionDTO';
import { Component, OnInit } from '@angular/core';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {

  fetchedPromotions: PromotionDTO[] = [];
  dataSource2: MatTableDataSource<PromotionDTO>;
  displayedColumns2: string[]= [
    'id',
    'dateFrom',
    'dateTo',
    'description'
  ];

  promotion: PromotionDTO = new PromotionDTO();
  clicked = false;
  pharmacyID: number;
  promotionForm : FormGroup

  constructor(
    private pharmacyService : PharmacyService,
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
      this.pharmacyID = this.route.snapshot.params.id;

      this.promotionForm = this.formBuilder.group({
        description: ['', Validators.required],
        dateTo: ['', Validators.required],
        dateFrom: ['', Validators.required]

      });

      this.fetchPromotions();

  }

  showForm(){
    this.clicked = true;
  }

  fetchPromotions(){
    this.pharmacyService.fetchAllPromotions(this.pharmacyID).subscribe(response => {
      this.fetchedPromotions = response;
      this.dataSource2= new MatTableDataSource(this.fetchedPromotions);
    });

  }
  createNewPromotion(){

      this.clicked = false;

      this.pharmacyService.createPromotion(this.pharmacyID,this.promotion).subscribe(response => {
        alert("Created!");
        this.fetchPromotions();
      });

  }

  backToDetails(){
    this.router.navigate(['/pharmacy-details/' + this.pharmacyID]);
  }
}
