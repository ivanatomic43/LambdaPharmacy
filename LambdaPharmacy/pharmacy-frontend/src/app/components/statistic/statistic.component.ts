import { StatisticService } from './../../services/StatisticService';
import { MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { AuthService } from 'src/app/services/AuthService';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { DermDTO } from 'src/app/model/DermDTO';
import { AppointmentStatistic } from 'src/app/model/AppointmentStatistic';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {

  pharmacyID: number;
  fetchedPharmacy: PharmacyDTO;

  dataSource: MatTableDataSource<DermDTO>;
  fetchedStaff : DermDTO[] = [];
  displayedColumns : string[] = [
    'id',
    'employee',
    'role',
    'rating'
  ];

  //charts
  appointmentList : Array<AppointmentStatistic> = [];
  medicinesList : Array<AppointmentStatistic> = [];
  earningsList : Array<AppointmentStatistic> = [];
  barChartOptions : ChartOptions = {
    responsive: true,
  };
  barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit', 'Blueberry', 'Orange', 'Grapes'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: ChartDataSets[] = [];
  dataList : number[] = [];
  chart : boolean = false;
  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgb(15, 102, 116, 0.5)',
      borderColor: 'rgb(15, 102, 116)',
      borderWidth: 3,
    }
  ];


  constructor(
    private pharmacyService : PharmacyService,
    private router : Router,
    private route : ActivatedRoute,
    private authService : AuthService,
    private statisticService : StatisticService
  ) { }

  ngOnInit() {

    this.pharmacyID = this.route.snapshot.params.id;

    this.pharmacyService.getPharmacyById(this.pharmacyID).subscribe(response =>{
        this.fetchedPharmacy= response;
    });

    this.pharmacyService.getEmployedStaff(this.pharmacyID).subscribe(response => {
      this.fetchedStaff = response ;
      this.dataSource = new MatTableDataSource(this.fetchedStaff);
    });

  }

  appointments(){
    this.dataList = [];
    this.chart = true;
    this.barChartData = [];

    this.statisticService.appointments(this.pharmacyID).subscribe(response => {

      this.barChartLabels = [];
      this.appointmentList = response as Array<AppointmentStatistic>;

      for(var appointment of this.appointmentList) {


        this.barChartLabels.push(appointment.month);
        this.dataList.push(appointment.appointmentCounter);
      }
    }, error => {
      alert("Error");
    });

    this.barChartData.push({data: this.dataList, label: 'Appointments statistic'});

  }

  medicines(){
    this.dataList = [];
    this.chart = true;
    this.barChartData = [];

    this.statisticService.medicines(this.pharmacyID).subscribe(response => {

      this.barChartLabels = [];
      this.medicinesList = response as Array<AppointmentStatistic>;

      for(var medicine of this.medicinesList) {


        this.barChartLabels.push(medicine.month);
        this.dataList.push(medicine.appointmentCounter);
      }
    }, error => {
      alert("Error");
    });

    this.barChartData.push({data: this.dataList, label: 'Number of sold medicines per month'});
  }

  totalEarnings(){
    this.dataList = [];
    this.chart = true;
    this.barChartData = [];

    this.statisticService.totalEarnings(this.pharmacyID).subscribe(response => {

      this.barChartLabels = [];
      this.earningsList = response as Array<AppointmentStatistic>;

      for(var sum of this.earningsList) {


        this.barChartLabels.push(sum.month);
        this.dataList.push(sum.appointmentCounter);
      }
    }, error => {
      alert("Error");
    });

    this.barChartData.push({data: this.dataList, label: 'Total earnings per month'});
  }

  backToDetails(id:number){
    this.router.navigate(['/pharmacy-details/'+this.pharmacyID]);
  }



}
