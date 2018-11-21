import { PaymentDetails } from "./payment-details";

export class Magazine {
    constructor(
        public issn: string,
        public title: string,
        public details: PaymentDetails[]
    ) {}

    public print(): String{
        let str = "ISSN: "+this.issn 
            + "\nTitle: "+this.title
            +"\nTypes: ";
        for(let detail of this.details){
            str += detail.type + ",";
        }

        return str;
    }
}
