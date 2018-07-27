import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-vheicles',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {
  vehicles: Vehicle[];

  // For filtering
  customers: Customer[];
  pages: number[];
  currentPage: number;
  pageUrl: string = 'vehicle/';


  constructor(private vheicleService: VehicleService, private customerService: CustomerService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.getvheicles();
    this.getcustomers();
  }

  getcustomers(): void {
    this.customerService.getAllCustomers()
      .subscribe(page => this.customers = page.content);
  }
  /**
   *  
   */
  getvheicles(): void {
    const status = this.route.snapshot.paramMap.get('status');
    this.route
      .queryParams
      .subscribe(params => {
        this.currentPage = +params['page'] || 0;
      });
    this.loadData(status);

  }

  loadData(status: string) {
    if (status) {

      this.vheicleService.getVehiclesByStatus(this.currentPage, status)
        .subscribe(page => {
          this.vehicles = page.content;
          this.pages = [];
          var i = 0;
          for (; i < page.totalPages; i++) {
            this.pages.push(i);
          }
        }
        );
      this.pageUrl = 'vehicle/' + status;
      return;
    }

    this.vheicleService.getVehicles(this.currentPage)
      .subscribe(page => {
        this.vehicles = page.content;
        this.pages = [];
        var i = 0;
        for (; i < page.totalPages; i++) {
          this.pages.push(i);
        }
      }
      );
  }
  /**
   * 
   * @param value The value of the Status Change
   */
  onStatusChange(value: any) {
    if (value == 'ALL') {
      this.pageUrl = 'vehicle/';
      this.loadData(undefined)
    } else {
      this.pageUrl = 'vehicle/' + value;
      this.loadData(value)
    }
  }

  /**
   * 
   * @param customerID 
   */
  onChangeCustomer(customerID: string) {
    this.vheicleService.getVehiclesByCustomer(customerID)
      .subscribe(page => {
        this.vehicles = page.content;
        this.pages = [];
        var i = 0;
        for (; i < page.totalPages; i++) {
          this.pages.push(i);
        }
      }
      );
  }

  add(): void {
    this.router.navigate(['/vehicledetail/0']);
  }

  delete(vheicle: Vehicle): void {
    this.vehicles = this.vehicles.filter(v => v !== vheicle);
    this.vheicleService.deleteVehicle(vheicle.id).subscribe();
  }

  public page(pageNum: number) {
    this.currentPage = pageNum;
    this.router.navigate([this.pageUrl], { queryParams: { page: pageNum } });
  }

}