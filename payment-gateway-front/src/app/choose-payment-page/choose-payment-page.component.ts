import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Merchandise } from '../model/merchandise';
import { Order } from '../model/order';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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
  headers: HttpHeaders;
  
  paypalConfig = {
    env: 'sandbox',
    commit: true,
    payment: function (data, actions) {
      return actions.request({
          url: "http://localhost:8080/api/orders",
          method: 'POST',
          json: {
            merchantOrderId: "31231231321",
            payerID: "2313",
            merchantId: "12345678",
            amount: 1,
            type: "PAY_PAL"
          },
          contentType: 'application/json'
      })
        .then(function(res){
          return res.id;
        })
    },
    onAuthorize: function(data, actions) {
     
      // 2. Make a request to your server
      return actions.request({
        url: "http://localhost:8080/api/orders",
        method: "PUT",
        json: {
          merchantOrderId: data.paymentID,
          payerId: data.payerID,
          type: "PAY_PAL"/*,
          amount: 1*/
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
      merchantId: "xxWWyyZZ"
    };
    
    this.order = new Order("-1", Date.now(), "1234ABCD", "", 0, "CreditCard");

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
  public pay(type: string){
    this.order.type = type;
    console.log(type);
    console.log(this.merchandise.price);
    if(type == "Bitcoin"){
      console.log(this.merchandise.price);
      this.http.post("http://localhost:8080/api/bitcoinOrder/bitcoin",this.merchandise)
      .subscribe(
          data => {
              console.log("POST Request is successful ", data);

              this.router.navigate(['/externalRedirect', { externalUrl: "\""+data+"\"" }]);
          },
          error => {
              console.log("Error", error);
          }
      );    
    
    }

  }
  public createOrder(){
    
    /*this.order.merchantOrderId = Math.floor(Math.random()+1);
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
    }*/
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
