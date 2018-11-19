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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ChoosePaymentPageComponent,
    SubscribeMagazinePageComponent,
    SuccessPageComponent,
    CancelPageComponent,
    ErrorPageComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFontAwesomeModule,
    TooltipModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
