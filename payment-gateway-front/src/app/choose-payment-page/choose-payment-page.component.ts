import { Component, OnInit } from '@angular/core';
import { Merchandise } from '../model/merchandise';
import { Order } from '../model/order';
import { Router } from '@angular/router';

@Component({
  selector: 'app-choose-payment-page',
  templateUrl: './choose-payment-page.component.html',
  styleUrls: ['./choose-payment-page.component.css']
})
export class ChoosePaymentPageComponent implements OnInit {

  merchandise: Merchandise;
  order: Order;
  paymentTypes: any[];

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
