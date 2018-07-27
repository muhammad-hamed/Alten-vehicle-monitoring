import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customers: Customer[];
  pages:number[];
  currentPage:number;

  constructor(private customerService: CustomerService,  private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.getcustomers();
  }

  getcustomers(): void {
    const page = +this.route.snapshot.paramMap.get('page');
    this.currentPage = page ;
      this.customerService.getCustomers(page)
    .subscribe(page => {
      this.customers = page.content;
      this.pages = [];
      var i = 0;
      for (;i < page.totalPages ; i++) {
        this.pages.push(i);
      }
    });
  }

  add(): void {
    this.router.navigate(['/custdetail/0']);
  }

  delete(customer: Customer): void {
    this.customers = this.customers.filter( cust => cust !== customer);
    this.customerService.deleteCustomer(customer.id).subscribe();
  }

}