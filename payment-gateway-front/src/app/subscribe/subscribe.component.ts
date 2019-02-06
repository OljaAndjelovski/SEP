import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Merchandise } from '../model/merchandise';
import { Order } from '../model/order';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

declare let paypal: any;

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit, AfterViewChecked {

  addScript: boolean = false;
  paypalLoad: boolean = true;
  merchandise: Merchandise;
  order: Order;
  paymentTypes: any[];
  headers: HttpHeaders;

  paypalConfig = {
    env: 'sandbox',
    commit: true,
    payment: function (data, actions) {
      return actions.request({
        url: "https://localhost:8080/api/subscribe/12345678",
        method: 'POST'
      })
        .then(function (res) {
          return res.id;
        })
    },
    onAuthorize: function (data, actions) {

      // 2. Make a request to your server
      return actions.request({
        url: "https://localhost:8080/api/subscribe//execute",
        method: "PUT",
        json: {
          merchantOrderId: data.paymentID,
          payerId: data.payerID,
          type: "PAY_PAL"
        },
        contentType: 'application/json'
      })
        .then((payment) => {
          console.log(payment);
          this.router.navigate(['/success']);
        });
    }
  };

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.headers = new HttpHeaders();
    this.headers.append("Content-Type", "appication/json");
  }

  ngOnInit() {
    this.merchandise = {
      name: "Default magazine",
      description: "This is a default magazine, only present in development mode",
      quantity: 1,
      price: 42175,
      currency: "RSD",
      merchantId: "xxWWyyZZ",
      type: "dsads",
      buyerEmail : "dsada",
      buyerName : "dsada",
      buyerSurname : "dsada",
      payerId : "dasd",
      productId : 4,
      username : "dsad"
    };

    this.order = new Order("-1", Date.now(), "1234ABCD", "", 0, "CreditCard", "");

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
  public pay(type: string) {
    this.order.type = type;
    // console.log(type);
    // console.log(this.merchandise);
    if (type == "Bitcoin") {
      console.log(this.merchandise.price);
      this.http.post("https://localhost:8080/api/bitcoinOrder/bitcoin", this.merchandise)
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);

            let d: string = "/'" + data + "/'";
            console.log(d);
            window.location.href = data.toString();
            //document.location.href = "\'"+data + "\'"; 
            //this.router.navigate(['/externalRedirect', { externalUrl: 'https://sandbox.coingate.com/invoice/cf6e91fb-3a05-4468-ae84-0420240fbf66' }]);
          },
          error => {
            console.log("Error", error);
          }
        );

    } else if (type == 'CreditCard') {

      let order = new Order();
      order.amount = 1;
      order.merchantId = "123123";
      order.merchantOrderId = "31231231321";
      order.payerId = "78946";
      order.type = "CREDIT_CARD";

      this.http.post("https://localhost:8080/api/orders", order)
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
          },
          error => {
            console.log("Error", error);
          }
        );
    }

  }
  public createOrder() {
    this.http.get("http://localhost:8080/api/orders").subscribe(
      (data) => {
        this.router.navigate(['/success'])
      },
      error => {
        this.router.navigate(['/error'])
      }
    )
  }
}
