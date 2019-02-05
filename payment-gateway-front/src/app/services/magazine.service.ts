import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Magazine } from '../model/magazine';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Magazine[]>{
    return this.http.get<Magazine[]>("https://localhost:8080/magazines/getMagazines");
  }
}
