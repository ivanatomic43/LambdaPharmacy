export class AppointmentDDTO{

id: number;
type: string;
dateOfAppointment: Date;
duration: number;
dermatologistName: string;
dermatologistSurname: string;

constructor(id:number, type:string, dateOfAppointment:Date, duration:number, dermatologistName:string, dermatologistSurname:string){
  this.id=id;
  this.type=type;
  this.dateOfAppointment=dateOfAppointment;
  this.duration=duration;
  this.dermatologistName= dermatologistName;
  this.dermatologistSurname= dermatologistSurname;
}








}
