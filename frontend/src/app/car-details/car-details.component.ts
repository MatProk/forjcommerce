import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CarService } from '../services/car.service';
import { CarExample } from '../services/car.resource';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {

  car = new CarExample();
  hasToken: any;
  errorinfo: string = null;

  constructor(private carService: CarService,
              private route: ActivatedRoute,
              private router: Router,
              private token: TokenStorageService) {

    this.route.params.subscribe(params => {
      this.carService.getOneCar(params['id']).subscribe(data => {
        console.log(data);
        this.car = data})
    });
  }

  ngOnInit() {
  }

}
