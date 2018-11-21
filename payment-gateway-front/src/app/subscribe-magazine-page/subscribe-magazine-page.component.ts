import { Component, OnInit } from '@angular/core';
import { Magazine } from '../model/magazine';
import { PaymentDetails } from '../model/payment-details';
import { Router } from '@angular/router';

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

  constructor(
    private router: Router
  ) { }

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
  }

  openType(type: string){
    if(!this.paymentTypeChecked(type)){
      this.chosenType = type;
    }
  }

  saveMagazine(){
    if(window.confirm("Do you want to save this magazine?\n"+this.magazine.print())){
      this.router.navigate(["/home"]);
    }
  }

}
