import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(
    private http: HttpClient
    ) { }

  public getPaymentTypes(): Observable<any>{
    return this.http.get("http://localhost:8080/api/types");
  }
}
