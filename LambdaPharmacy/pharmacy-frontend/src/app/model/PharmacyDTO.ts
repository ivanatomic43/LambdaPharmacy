export class PharmacyDTO {


   constructor(
     public id: number,
     public name: string,
     public street: string,
     public city: string,
     public latitude : any,
     public longitude : any,
     public description: string,
     public rating:number,
     public price: number) {


   }

}
