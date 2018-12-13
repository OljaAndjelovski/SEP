import { PaymentDetails } from "./payment-details";

export class Magazine {
    constructor(
        public issn: string,
        public title: string,
        public details: PaymentDetails[]
    ) {}

    public print(): String{
        let str = "ISSN: ISSN "+this.issn[0]+this.issn[1]+this.issn[2]+this.issn[3]+'-'+this.issn[4]+this.issn[5]+this.issn[6]+this.issn[7] 
            + "\nTitle: "+this.title
            +"\nTypes: ";
        for(let detail of this.details){
            str += detail.type + ",";
        }

        return str;
    }
}
