import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { TooltipModule } from 'ngx-bootstrap/tooltip';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ChoosePaymentPageComponent } from './choose-payment-page/choose-payment-page.component';
import { SubscribeMagazinePageComponent } from './subscribe-magazine-page/subscribe-magazine-page.component';
import { SuccessPageComponent } from './end-pages/success-page/success-page.component';
import { CancelPageComponent } from './end-pages/cancel-page/cancel-page.component';
import { ErrorPageComponent } from './end-pages/error-page/error-page.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { PageNotFoundComponent } from './end-pages/page-not-found/page-not-found.component';
import { StartPageComponent } from './end-pages/start-page/start-page.component';
<<<<<<< HEAD
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
=======
import { OrderService } from './services/order.service';
import { PaymentService } from './services/payment.service';
import { MagazineService } from './services/magazine.service';
import { PaymentDetailsService } from './services/payment-details.service';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { RouterModule } from '@angular/router';
>>>>>>> ec50c9c59851f531603c2b2d929b1df256ae6268

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ChoosePaymentPageComponent,
    SubscribeMagazinePageComponent,
    SuccessPageComponent,
    CancelPageComponent,
    ErrorPageComponent,
    PageNotFoundComponent,
    StartPageComponent,
<<<<<<< HEAD
=======
    SubscribeComponent
>>>>>>> ec50c9c59851f531603c2b2d929b1df256ae6268
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFontAwesomeModule,
    NgbModule,
    TooltipModule.forRoot()
  ],
<<<<<<< HEAD
  providers: [],
  bootstrap: [AppComponent],
=======
  providers: [OrderService, PaymentService, MagazineService, PaymentDetailsService],
  bootstrap: [AppComponent]
>>>>>>> ec50c9c59851f531603c2b2d929b1df256ae6268
})
export class AppModule { }
