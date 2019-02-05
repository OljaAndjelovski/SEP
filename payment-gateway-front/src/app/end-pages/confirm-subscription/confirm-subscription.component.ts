import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/services/payment.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-confirm-subscription',
  templateUrl: './confirm-subscription.component.html',
  styleUrls: ['./confirm-subscription.component.css']
})
export class ConfirmSubscriptionComponent implements OnInit {

  token: string = "";
  magazine: string = "";

  constructor(
    private paymentService: PaymentService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token'];
      this.magazine = params['magazine'];
    });
  }

  execute(){
    this.paymentService.executeSubscription(this.magazine, this.token).subscribe(
      (data) => {
        this.router.navigate(['success']);
      },
      (error) => {
        this.router.navigate(['error']);
      }
    )
  }

  cancel() {
    this.router.navigate(['cancel']);
  }

}
