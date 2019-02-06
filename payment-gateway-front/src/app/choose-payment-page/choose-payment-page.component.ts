import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Merchandise } from '../model/merchandise';
import { Order } from '../model/order';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { PaymentDetailsService } from '../services/payment-details.service';

declare let paypal: any;

@Component({
  selector: 'app-choose-payment-page',
  templateUrl: './choose-payment-page.component.html',
  styleUrls: ['./choose-payment-page.component.css']
})
export class ChoosePaymentPageComponent implements OnInit, AfterViewChecked {
  closeResult: string;
  addScript: boolean = false;
  paypalLoad: boolean = true;
  merchandise: Merchandise;
  order: Order;
  paymentTypes: any[];
  headers: HttpHeaders;

  paypalConfig = {
    env: 'sandbox',
    commit: true,
    payment: (data, actions) => {
      return actions.request({
        url: "https://localhost:8080/api/orders",
        method: 'POST',
        json: {
          merchantOrderId: null,
          payerId: this.order.payerId,
          merchantId: this.merchandise.merchantId,
          amount: this.merchandise.price,
          currency: this.merchandise.currency,
          type: "PAY_PAL",
          merchandise: this.merchandise.name
        },
        contentType: 'application/json'
      })
        .then(function (res) {
          return res.id;
        })
    },
    onAuthorize: (data, actions) => {

      // 2. Make a request to your server
      return actions.request({
        url: "https://localhost:8080/api/orders",
        method: "PUT",
        json: {
          merchantOrderId: data.paymentID,
          payerId: data.payerID,
          merchantId: this.merchandise.merchantId,
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
    private http: HttpClient,
    // private modalService: NgbModal
    private paymentService: PaymentDetailsService
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
      type : "dsada",
      buyerEmail : "dsada",
      buyerName : "dsada",
      buyerSurname : "dsada",
      payerId : "dsad",
      productId : 2,
      username : "dsdad"
    };

    this.order = new Order("-1", Date.now(), "1234ABCD", "", 0, "CreditCard", "");

    let parts = window.location.href.split('/');
    this.paymentTypes = [
      {
        name: "Credit Card",
        code: "CREDIT_CARD"
      },
      {
        name: "Pay Pal",
        code: "PAY_PAL"
      },
      {
        name: "Bitcoin",
        code: "BITCOIN"
      }
    ]


    /*

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
      payerId : "dsada"

    */
    this.http.get<any>("https://localhost:8080/sessions/" + parts[parts.length-1])
      .subscribe((data) => {
        alert(JSON.stringify(data))
        this.merchandise.name = data.merchandise;
        this.merchandise.description = data.description;
        this.merchandise.quantity = data.quantity;
        this.merchandise.price = data.price;
        this.merchandise.currency = data.currency;
        this.merchandise.merchantId = data.issn;
        this.merchandise.type = data.type;
        this.merchandise.buyerEmail = data.buyerEmail;
        this.merchandise.buyerName = data.buyerName;
        this.merchandise.buyerSurname = data.buyerSurname;
        this.merchandise.payerId = data.payerId;
        this.merchandise.productId = data.productId;
        this.merchandise.username = data.username

        alert(Merchandise)
        this.order.payerId = data.username;
        
        alert(JSON.stringify(this.merchandise))
    this.paymentService.getTypesOfMagazine(this.merchandise.merchantId).subscribe(
       (data) => {
         this.paymentTypes = data;
       }
     );
   });
  }

  ngAfterViewChecked(): void {
    if (!this.addScript) {
      this.addPaypalScript().then(() => {
        paypal.Button.render(this.paypalConfig, '#paypal-button');
        this.addScript = true;
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
  public pay(type: string, content) {
    this.order.type = type;
    // console.log(type);
    // console.log(this.merchandise);
    if (type == "BITCOIN") {
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

    } else if (type == 'CREDIT_CARD') {

      // this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      // console.log(`Closed with: ${result}`);
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
      // }, (reason) => {
      //   console.log(`Dismissed ${this.getDismissReason(reason)}`);
      // });
    }
  }

  // private getDismissReason(reason: any): string {
  //   if (reason === ModalDismissReasons.ESC) {
  //     return 'by pressing ESC';
  //   } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
  //     return 'by clicking on a backdrop';
  //   } else {
  //     return `with: ${reason}`;
  //   }
  // }

  public createOrder() {

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
