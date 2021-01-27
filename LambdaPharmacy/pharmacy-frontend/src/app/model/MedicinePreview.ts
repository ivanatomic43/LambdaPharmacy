export class MedicinePreview{
  constructor(
    public id: number,
    public medicineCode: string,
    public medType: string,
    public name: string,
    public shape: string,
    public producer: string,
    public structure: string,
    public mode: string,
    public note: string,
    public pharmacyID:number,
    public pharmacyName: string,
    public status: string,
    public contraidications: string,
    public dailyDose : string,
    public quantity: number,
    public price: number,
    public rating: number,
    public priceFrom: string,
    public priceTo: string,
    public myRate: string
  ) {
  }



}
