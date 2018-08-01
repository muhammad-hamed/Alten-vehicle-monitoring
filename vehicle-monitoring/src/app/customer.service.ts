import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { Page } from './Page';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  host: string = environment.url;
  baseUrl: string = this.host + '/customer/customer';

  constructor(private http: HttpClient) { }

  getCustomers(page: number): Observable<Page<Customer>> {
    return this.http.get<Page<Customer>>(`${this.baseUrl}?page=${page}`);
  }

  getAllCustomers(): Observable<Page<Customer>> {
    return this.http.get<Page<Customer>>(`${this.baseUrl}?page=0&size=100000`);
  }


  getCustomer(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.baseUrl}/${id}`);
  }

  addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.baseUrl, customer);
  }

  updateCustomer(customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(this.baseUrl, customer);
  }

  deleteCustomer(id: number): Observable<Customer> {
    return this.http.delete<Customer>(`${this.baseUrl}/${id}`);
  }


  search(name: string): Observable<any> {
    if (!name.trim()) {
      return of([]);
    }
    return this.http.get<Customer>(`${this.baseUrl}/search?name=${name}`);
  }

  private log(message: string) {
    console.log(`customerService: ${message}`);
  }

}
