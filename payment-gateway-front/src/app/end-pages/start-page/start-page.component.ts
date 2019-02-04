import { Component, OnInit } from '@angular/core';
import { Magazine } from 'src/app/model/magazine';
import { MagazineService } from 'src/app/services/magazine.service';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-start-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.css']
})
export class StartPageComponent implements OnInit {

  magazines: Magazine[];

  constructor(
    private magazineService: MagazineService,
    private paymentService: PaymentService
  ) { }

  ngOnInit() {
    this.magazineService.getAll().subscribe(
      (data) => {
        this.magazines = data;
      }
    )
  }

  subscribe(issn: string){
    this.paymentService.createSubscription(issn).subscribe(
      (data) => {
        window.location.href = data.link;
      }
    )
  }

}
