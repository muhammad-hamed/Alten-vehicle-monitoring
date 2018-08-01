import { Injectable } from '@angular/core';
import { Vehicle } from './vehicle';
import { Page } from './Page';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { VehicleStats } from './vehicle.stats';
import { environment } from '../environments/environment';



@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  baseUrl: string = environment.url + '/vehicle/vehicle';

  constructor(private http: HttpClient) { }

  getVehicles(page: number): Observable<Page<Vehicle>> {
    return this.http.get<Page<Vehicle>>(`${this.baseUrl}?page=${page}`);
  }

  getVehiclesByStatus(page: number, status:string): Observable<Page<Vehicle>> {
    return this.http.get<Page<Vehicle>>(`${this.baseUrl}/search/status/${status}?page=${page}`);
  }

  getVehiclesByCustomer(customerID:string): Observable<Page<Vehicle>> {
    return this.http.get<Page<Vehicle>>(`${this.baseUrl}/search/customerID/${customerID}?size=1000`);
  }

  getVehicle(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(`${this.baseUrl}/${id}`);
  }


  getVehicleStats(): Observable<VehicleStats> {
    return this.http.get<VehicleStats>(`${this.baseUrl}/stats`);
  }

  save(Vehicle: Vehicle): Observable<any> {
    return this.http.post<Vehicle>(this.baseUrl, Vehicle);
  }

  updateVehicle(customer: Vehicle): Observable<Vehicle> {
    return this.http.put<Vehicle>(this.baseUrl, customer);
  }

  deleteVehicle(id: number): Observable<Vehicle> {
    return this.http.delete<Vehicle>(`${this.baseUrl}/${id}`);
  }


  private log(message: string) {
    console.log(`VehicleService: ${message}`);
  }

}
