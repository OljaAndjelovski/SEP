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

  issnMsg: string;
  issnValid: boolean;

  issn1: string;
  issn2: string;

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
  
    this.magazine = new Magazine("", "", []);
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
      
      
      this.magazine.details.push(
        new PaymentDetails(this.chosenType, this.newID, this.newPassword, this.magazine.details.length+1)
      );
      
      this.initForm();
    }
  }

  initForm(){
    this.chosenType = "";
    this.newID = "";
    this.newPassword = "";
    this.issnMsg = "";
    this.issnValid = true;
    this.issn1 = "";
    this.issn2 = "";
  }

  openType(type: string){
    
    if(!this.paymentTypeChecked(type)){
      this.chosenType = type;
    }
  }

  saveMagazine(){
    if(this.issnValid == true &&  this.magazine.title.length > 0){
      this.magazine.issn = this.issn1+this.issn2;
      if(window.confirm("Do you want to save this magazine?\n"+this.magazine.print())){
        for(let detail of this.magazine.details){
        
          if(detail.type=="PayPal"){
            detail.type = "PAY_PAL";
            console.log("pp");
          }
          else if(detail.type=="CreditCard"){
            detail.type = "CREDIT_CARD";
            console.log("cc");
          }
          else{
            detail.type = "BITCOIN";
            console.log("btc");
          }
        }

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

  checkValidIssn() {
    if(this.issn1.length + this.issn2.length < 8) {
      this.issnValid = false;
      this.issnMsg = "ISSN needs to be 8 characters long."
      return;
    }

    let factors = [8, 7, 6, 5, 4, 3, 2];
    let digits = [];
    let i = 0;

    for(; i<this.issn1.length; i++){
      const digit = Number.parseInt(this.issn1[i]);
      if(isNaN(digit)){
        this.issnValid = false;
        this.issnMsg = "ISSN is consisted of 8 digits or 7 digits and a X as the last character.";
        return;
      }
      digits.push( digit * factors[i]);
    }

    for(;i<this.issn2.length+3; i++){
      const digit = Number.parseInt(this.issn2[i-4]);
      if(isNaN(digit)){
        this.issnValid = false;
        this.issnMsg = "ISSN is consisted of 8 digits or 7 digits and a X as the last character.";
        return;
      }
      digits.push( digit * factors[i]);
    }

    let finDigit;
    if(this.issn2[3].toUpperCase() === 'X'){
      finDigit = 10;
    }else{
      finDigit = Number.parseInt(this.issn2[3]);
    }

    const add = (a, b) => a + b

    const sum = digits.reduce(add);

    if((11 - (sum % 11)) !== finDigit){
      this.issnMsg = "Issn is not valid. Please check entered values.";
      this.issnValid = false;
      return;
    }

    this.issnMsg = "";
    this.issnValid = true;
  }
}
