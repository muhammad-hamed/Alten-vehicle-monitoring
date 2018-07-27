import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {Customer} from '../customer'
import {CustomerService} from '../customer.service'
import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';
@Component({
  selector: 'app-customer-search',
  templateUrl: './customer-search.component.html',
  styleUrls: ['./customer-search.component.css']
})
export class CustomerSearchComponent implements OnInit {
  customers$:Observable<Customer>;
  private searchName = new Subject<string>();

  constructor(private customerService:CustomerService) { 

  }

  search(name: string): void {
    this.searchName.next(name);
  }
 
  ngOnInit(): void {
    this.customers$ = this.searchName.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),
 
      // ignore new term if same as previous term
      distinctUntilChanged(),
 
      // switch to new search observable each time the term changes
      switchMap((name: string) => this.customerService.search(name)),
    );
  }
  

}
