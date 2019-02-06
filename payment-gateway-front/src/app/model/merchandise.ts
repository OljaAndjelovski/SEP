export class Merchandise {

    constructor(
        public name: string,
        public description: string,
        public quantity: number,
        public price: number,
        public currency: string,
        public merchantId: string,
        public type : string,
        public buyerEmail : string,
        public buyerName : string,
        public buyerSurname : string,
        public payerId : string,
        public productId : string

    ) {}
}
