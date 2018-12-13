import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private http: HttpClient
  ) { 
  }

  public createOrder(actions: any, o: Order): Promise<any>{
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json");
    return this.http.post("http://localhost:8080/api/orders", 
      JSON.stringify({
        merchantOrderId: o.merchantOrderId,
        payerID: o.payerId,
        merchantId: o.merchantId,
        amount: o.amount,
        type: o.type,
        currency: o.currency
      }), { headers }).toPromise();
  }

  public executeOrder(actions: any, o: Order): Promise<any>{
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json");
    return this.http.put("http://localhost:8080/api/orders",
      JSON.stringify({
        merchantOrderId: o.merchantOrderId,
        payerId: o.payerId,
        type: o.type
      }), { headers }).toPromise();
  }


}
