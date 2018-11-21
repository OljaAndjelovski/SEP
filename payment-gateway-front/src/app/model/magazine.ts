import { PaymentDetails } from "./payment-details";

export class Magazine {
    constructor(
        public issn: string,
        public title: string,
        public details: PaymentDetails[]
    ) {}
}
