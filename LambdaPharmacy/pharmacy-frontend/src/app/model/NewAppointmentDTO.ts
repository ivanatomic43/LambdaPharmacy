export class NewAppointmentDTO{

  constructor(
    public pharmacyID: number,
    public dermatologistID: number,
    public dateOfAppointment: any,
    public meetingTime: any,
    public duration: string,
    public price: string
  ){}





}
