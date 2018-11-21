export class Merchandise {

    constructor(
        public name: string,
        public description: string,
        public quantity: number,
        public price: number,
        public currency: string,
        public merchantId: string
    ) {}
}
