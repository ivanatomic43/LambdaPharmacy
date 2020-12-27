export class AppointmentPreview{

  id:number;
  pharmacyID: number;
  dermatologistID: number;
  pharmacistID : number;

  dateOfAppointment:any;
  meetingTime: any;
  duration: number;
  price: number;

  pharmacyName: string;
  firstName: string;
  lastName: string;
  rating: number;

  constructor(id: number, dateOfAppointment:any, meetingTime:any, firstName:string, lastName: string, duration:number, price:number, rating:number){
    this.id=id;
    this.dateOfAppointment= dateOfAppointment;
    this.meetingTime= meetingTime;
    this.firstName=firstName;
    this.lastName= lastName;
    this.duration=duration;
    this.price=price;
    this.rating=rating;
  }



}
