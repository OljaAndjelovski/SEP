import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Magazine } from '../model/magazine';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(
    private http: HttpClient
    ) { }

  public getPaymentTypes(): Observable<any>{
    return this.http.get("https://localhost:8080/api/types");
  }

  public createSubscription(issn: string): Observable<any> {
    return this.http.post(`https://localhost:8080/api/subscribe/${issn}`, {});
  }

  public executeSubscription(issn: string, paymentID: string): Observable<any> {
    return this.http.put(`https://localhost:8080/api/subscribe/${paymentID}/execute`, issn);
  }
}
