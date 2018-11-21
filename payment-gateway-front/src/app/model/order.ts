export class Order {

    constructor(
        public merchantOrderId: number,
        public merchantTimestamp: number,
        public payerId: string,
        public merchantId: string,
        public amount: number,
        public type: string
    ) {}

    public print(): String{
        return "Timestamp: "+this.merchantTimestamp
                +"\nPayer: "+this.payerId
                +"\nMerchant: "+this.merchantId
                +"\nAmount: "+this.amount
                +"\nPayment type: "+this.type;
    }
}
