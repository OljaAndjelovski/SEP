import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Merchandise } from '../model/merchandise';
import { Order } from '../model/order';
import { Router } from '@angular/router';

declare let paypal: any;

@Component({
  selector: 'app-choose-payment-page',
  templateUrl: './choose-payment-page.component.html',
  styleUrls: ['./choose-payment-page.component.css']
})
export class ChoosePaymentPageComponent implements OnInit, AfterViewChecked {

  addScript: boolean = false;
  paypalLoad: boolean = true;
  merchandise: Merchandise;
  order: Order;
  paymentTypes: any[];
  
  paypalConfig = {
    env: 'sandbox',
    client: {
      sandbox: 'AS7kftlYLLaUYwYyYJMsW3K3piIompuO7yJl_5n7YbCBXTjW0WS17IahnEFoauQTOnc5kdrg1YeHHMQY'
    },
    commit: true,
    payment: (data, actions) => {
      return actions.payment.create({
        payment: {
          transactions: [
            { amount: {total: 500, currency: 'USD'}}
          ]
        }
      });
    },
    onAuthorize: function(data, actions) {
      // 2. Make a request to your server
      return actions.payment.execute()
        .then((payment) => {
            console.log(payment);
            this.router.navigate(['/success']);
        });
    }
  };

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.merchandise = {
      name: "Default magazine",
      description: "This is a default magazine, only present in development mode",
      quantity: 1,
      price: 42175,
      currency: "RSD",
      merchantId: "xxWWyyZZ"
    };
    
    this.order = new Order(-1, Date.now(), "1234ABCD", "", 0, "CreditCard");

    this.paymentTypes = [
      {
        "code": "CreditCard",
        "name": "Credit Card"
      },
      {
        "code": "PayPal",
        "name": "PayPal"
      },
      {
        "code": "Bitcoin",
        "name": "Bitcoin"
      }
    ];
  }

  ngAfterViewChecked(): void {
    if (!this.addScript) {
      this.addPaypalScript().then(() => {
        paypal.Button.render(this.paypalConfig, '#paypal-button');
        this.paypalLoad = false;
      })
    }
  }
  
  addPaypalScript() {
    this.addScript = true;
    return new Promise((resolve, reject) => {
      let scripttagElement = document.createElement('script');    
      scripttagElement.src = 'https://www.paypalobjects.com/api/checkout.js';
      scripttagElement.onload = resolve;
      document.body.appendChild(scripttagElement);
    })
  }

  public createOrder(){
    this.order.merchantOrderId = Math.floor(Math.random()+1);
    this.order.merchantTimestamp = Date.now();
    this.order.merchantId = this.merchandise.merchantId;
    this.order.amount = this.merchandise.quantity * this.merchandise.price;

    if(window.confirm("Are you sure you want to place the following order?\n"+this.order.print())){
      window.alert("Preparing your order");
      let rand = (Math.random() * 100) % 20;
      if(rand > 7){
        this.router.navigate(["/success"]);
      }else{
        this.router.navigate(["/error"]);
      }
    }else {
      this.router.navigate(["/cancel"]);
    }
  }
}
