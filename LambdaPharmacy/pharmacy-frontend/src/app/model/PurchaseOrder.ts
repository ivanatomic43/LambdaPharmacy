import { OrderMedicine } from './OrderMedicine';

export class PurchaseOrder{

   constructor(
     public items: Array<OrderMedicine>,
     public date: any
   ){}


}
