import { Component, OnInit } from '@angular/core';
import { Magazine } from '../model/magazine';
import { PaymentDetails } from '../model/payment-details';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-subscribe-magazine-page',
  templateUrl: './subscribe-magazine-page.component.html',
  styleUrls: ['./subscribe-magazine-page.component.css']
})
export class SubscribeMagazinePageComponent implements OnInit {

  magazine: Magazine;
  fullPaymentTypes: any[];
  paymentTypes: any[];
  chosenType: string;
  newID: string;
  newPassword: string;
  headers: HttpHeaders;
  constructor(
    private router: Router,
    private http: HttpClient
  ) {this.headers = new HttpHeaders();
    this.headers.append("Content-Type", "appication/json"); }

  ngOnInit() {
    this.initForm();
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

    this.fullPaymentTypes = [
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
  
    this.magazine = new Magazine("xxWWyyZZ", "Default magazine", []);
  }

  paymentTypeChecked(type: string): boolean{
   
    for(let detail of this.magazine.details){
      if(detail.type === type){
        return true;
      }
    }

    return false;
  }

  addDetails(){
    if(this.paymentTypeChecked(this.chosenType) === false && this.newID !== "" && this.newPassword !== ""){
      var type;
      if(this.chosenType=="PayPal"){
        type = "PAY_PAL";
        console.log("PPP");
      }
      else if(this.chosenType=="CreditCard"){
        type = "CREDIT_CARD";
      }
      else{
        type = "BITCOIN";
      }
      
      this.magazine.details.push(
        new PaymentDetails(type, this.newID, this.newPassword, this.magazine.details.length+1)
      );
      
      this.initForm();
    }
  }

  initForm(){
    this.chosenType = "";
    this.newID = "";
    this.newPassword = "";
  }

  openType(type: string){
    
    if(!this.paymentTypeChecked(type)){
      this.chosenType = type;
    }
  }

  saveMagazine(){
    
    if(window.confirm("Do you want to save this magazine?\n"+this.magazine.print())){
      this.http.post("http://localhost:8080/magazines",this.magazine)
      .subscribe(
          data => {
            this.router.navigate(["/home"]);
            
          },
          error => {
              console.log("Error", error);
          }
      );    
      //this.router.navigate(["/home"]);
    }
  }

}
