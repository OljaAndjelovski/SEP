export class Order {

    constructor(
        public merchantOrderId: string,
        public merchantTimestamp: number,
        public payerId: string,
        public merchantId: string,
        public amount: number,
        public type: string,
        public currency: string
    ) {}

    public print(): String{
        return "Timestamp: "+this.merchantTimestamp
                +"\nPayer: "+this.payerId
                +"\nMerchant: "+this.merchantId
                +"\nAmount: "+this.amount
                +"\nCurrency: "+this.currency
                +"\nPayment type: "+this.type;
    }
}
