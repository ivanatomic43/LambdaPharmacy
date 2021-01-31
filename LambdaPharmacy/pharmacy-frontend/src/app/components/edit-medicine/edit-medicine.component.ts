import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicineService } from 'src/app/services/medicineService';
import { MedicineDTO } from 'src/app/model/MedicineDTO';

@Component({
  selector: 'app-edit-medicine',
  templateUrl: './edit-medicine.component.html',
  styleUrls: ['./edit-medicine.component.css']
})
export class EditMedicineComponent implements OnInit {

  medicineID: number;
  pharmacyID : number;
  medicineData: any;
  id: number;
  code: string;
  medType : string;
  name: string;
  shape: string;
  producer: string;
  structure: string;
  medicineMode: any;
  note: string;
  contraindications:string;
  dailyDose: string;

  editMedicineForm: FormGroup;
  MedicineDTO : MedicineDTO;

  constructor(
    private route : ActivatedRoute,
    private router : Router,
    private medicineService : MedicineService,
    private formBuilder : FormBuilder
  ) { }

  ngOnInit() {

    this.medicineID = this.route.snapshot.params.mid;
    this.pharmacyID = this.route.snapshot.params.pid;

    this.editMedicineForm = this.formBuilder.group({
      id: ['', Validators.required],
      code: ['', Validators.required],
      medType: ['', Validators.required],
      name: ['', Validators.required],
      shape: ['', Validators.required],
      producer: ['', Validators.required],
      structure : ['', Validators.required],
      medicineMode: ['', Validators.required],
      contraindications : ['', Validators.required],
      dailyDose: ['', Validators.required],
      note: ['', Validators.required]
    });

    this.getMedicine(this.medicineID, this.pharmacyID);

  }

  getMedicine(medicineID:number, pharmacyID: number){

    this.medicineService.getMedicineForEdit(medicineID, pharmacyID).subscribe(response => {
      console.log(response);

      this.medicineData = Object.assign([], response);
      this.id = this.medicineData.id;
      this.code = this.medicineData.medicineCode;
      this.medType = this.medicineData.medType;
      this.name = this.medicineData.name;
      this.shape = this.medicineData.shape;
      this.producer=this.medicineData.producer;
      this.structure = this.medicineData.structure;
      this.medicineMode = this.medicineData.medicineMode;
      this.note = this.medicineData.note;
      this.contraindications= this.medicineData.contraindications;
      this.dailyDose = this.medicineData.dailyDose;

    });
  }

  editMedicine(){

    this.MedicineDTO = new MedicineDTO(
      this.editMedicineForm.get('id').value,
      this.editMedicineForm.get('code').value,
      this.editMedicineForm.get('medType').value,
      this.editMedicineForm.get('name').value,
      this.editMedicineForm.get('shape').value,
      this.editMedicineForm.get('producer').value,
      this.editMedicineForm.get('structure').value,
      this.editMedicineForm.get('medicineMode').value,
      this.editMedicineForm.get('note').value,
      this.editMedicineForm.get('contraindications').value,
      this.editMedicineForm.get('dailyDose').value
    );

    this.medicineService.editMedicine(this.MedicineDTO, this.pharmacyID).subscribe(response => {
      alert("Medicine changed!");
      this.router.navigate(['/medicine-details/' + this.medicineID + '/' + this.pharmacyID]);
    });



  }

  cancelEditMedicine(){
    this.router.navigate(['/medicine-details/' + this.medicineID + '/'+ this.pharmacyID]);
  }

}
