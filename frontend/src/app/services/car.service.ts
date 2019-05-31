import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CarExample } from './car.resource';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CarService {
  private carsApi = 'http://localhost:8080/cars';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type':  'application/json',
  //     'Authorization': 'Baerer ' + this.tokenService.getToken()
  //   })
  // };
  // constructor(private http: HttpClient, private tokenService: TokenStorageService) { }
  constructor(private http: HttpClient) { }
  addCar(car: CarExample): Observable<CarExample> {
    return this.http.post<CarExample>(this.carsApi, car, this.httpOptions);
  }
  getOneCar(carId: number): Observable<CarExample> {
    return this.http.get<CarExample>(this.carsApi + '/' + carId, this.httpOptions);
  }
  getCars(): Observable<any> {
    return this.http.get(this.carsApi, this.httpOptions);
  }
  deleteCars(carId: number) {
    return this.http.delete(this.carsApi + '/' + carId, this.httpOptions);
  }
  updateCar(carId: number, car: CarExample): Observable<CarExample> {
    return this.http.put<CarExample>(this.carsApi + '/' + carId, car, this.httpOptions);
  }
}
