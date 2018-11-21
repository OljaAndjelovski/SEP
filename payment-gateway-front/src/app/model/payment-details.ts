export class PaymentDetails {
    constructor(
        public type: string,
        public merchantID?: string,
        public merchantPassword?: string,
        public id?: number
    ) {}
}
