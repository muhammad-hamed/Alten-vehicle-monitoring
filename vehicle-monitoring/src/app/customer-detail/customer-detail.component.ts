import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Customer }         from '../customer';
import { CustomerService }  from '../customer.service';
import { Address } from '../Address';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: [ './customer-detail.component.css' ]
})
export class CustomerDetailComponent implements OnInit {
  @Input() customer: Customer;

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getCustomer();
  }

  getCustomer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
   
    this.customerService.getCustomer(id)
      .subscribe(customer => this.customer = customer);
      if (id==0) {
        this.customer = new Customer();
        this.customer.address = new Address();
      }
  }

  goBack(): void {
    this.location.back();
  }

 save(): void {
    this.customerService.updateCustomer(this.customer)
      .subscribe(() => this.goBack());
  }


  getCustomerVehicles(customerID:number){
    
  }
}