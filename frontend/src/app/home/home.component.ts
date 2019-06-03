import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { CarService } from '../services/car.service';
import { FormControl, FormGroupDirective, NgForm, Validators, FormGroup } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material';
import { CarExample } from '../services/car.resource';
import { TokenStorageService } from '../auth/token-storage.service';
declare var bootbox:any;
import { ToastrService } from 'ngx-toastr'; 

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

export interface Fuel {
  value: string;
  viewValue: string;
}

export class SelectOverviewExample {
  fuels: Fuel[] = [
    {value: 'petrol', viewValue: 'Benzyna'},
    {value: 'diesel', viewValue: 'Olej napedowy'}
  ];
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  car = new CarExample();
  submitted = false;

  carBrandFormControl = new FormControl('', [
    Validators.required,
  ]);

  carModelFormControl = new FormControl('', [
    Validators.required,
  ]);

  engineFormControl = new FormControl('', [
    Validators.required,
  ]);

  powerHpFormControl = new FormControl('', [
    Validators.required,
  ]);

  descriptionFormControl = new FormControl('', [
    Validators.required,
  ]);

  fuelFormControl = new FormControl('', [
    Validators.required,
  ]);

  dateOfProductionFormControl = new FormControl('', [
    Validators.required,
  ]);

  matcher = new MyErrorStateMatcher();
  token;

  displayedColumns: string[] = ['id', 'carBrand', 'carModel', 'show', 'edit', 'delete'];
  dataSource = new MatTableDataSource();
  searchResult;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private toastr: ToastrService, private tokenStorage: TokenStorageService, private carService: CarService){}

  ngOnInit() {
    this.token = this.tokenStorage.getToken();
    this.carService.getCars().subscribe (res => {
      console.log(res);
      this.searchResult = res;
      this.dataSource.data = this.searchResult;
      this.dataSource = new MatTableDataSource(this.searchResult);
      this.dataSource.paginator = this.paginator;
    });
  }
  selectedValue: string;

  clients = [
    { id : '0', clientName: 'Benzyna'},
    { id : '1', clientName: 'Olej napedowy'}
  ];

  deleteCar(id: number){
    this.carService.deleteCar(id).subscribe(res => {
      this.toastr.error('Pomyslnie usunieto auto');
      this.carService.getCars().subscribe (res => {
        console.log(res);
        this.searchResult = res;
        this.dataSource.data = this.searchResult;
        this.dataSource = new MatTableDataSource(this.searchResult);
        this.dataSource.paginator = this.paginator;
      });
    });
  }

  addCar(){
    this.submitted = true;
    console.log(this.submitted);
    if(this.carBrandFormControl.invalid || this.carModelFormControl.invalid ||
       this.engineFormControl.invalid || this.powerHpFormControl.invalid ||
       this.descriptionFormControl.invalid || this.fuelFormControl.invalid ||
       this.dateOfProductionFormControl.invalid
      ){
      bootbox.alert("Nie wszystkie pola zostaly wypelnione!");
      return;
    }
    this.car.fuelType = this.selectedValue;
    console.log(this.car);
    this.carService.addCar(this.car).subscribe(res => {
      this.carBrandFormControl.reset();
      this.carModelFormControl.reset();
      this.engineFormControl.reset();
      this.fuelFormControl.reset();
      this.powerHpFormControl.reset();
      this.descriptionFormControl.reset();
      this.dateOfProductionFormControl.reset();
      this.toastr.success('Pomyslnie dodano auto', 'Sukces!');
      this.carService.getCars().subscribe (res => {
        console.log(res);
        this.searchResult = res;
        this.dataSource.data = this.searchResult;
        this.dataSource = new MatTableDataSource(this.searchResult);
        this.dataSource.paginator = this.paginator;
      });
      this.submitted = false;
      console.log(this.submitted);
      
    });
  }

  isEditing = false;

  updateCar(){
    let zmienna = this.car.id;
    console.log(this.car.id)
    this.carService.updateCar(zmienna, this.car).subscribe(data => {
      this.isEditing = false;
      this.carBrandFormControl.reset();
      this.carModelFormControl.reset();
      this.engineFormControl.reset();
      this.fuelFormControl.reset();
      this.powerHpFormControl.reset();
      this.descriptionFormControl.reset();
      this.dateOfProductionFormControl.reset();
      this.toastr.info('Pomyslnie zaktualizowano auto', 'Sukces!');
    })
}

startEdit(carId: number){
  this.carService.getOneCar(carId).subscribe(data => {
    console.log(data);
    this.car = data;

    let dateString = data.dateOfProduction.toString(); 
    this.car.dateOfProduction = new Date(dateString);

    if(data.fuelType == "DIESEL"){
      this.selectedValue = '1';
      console.log(this.selectedValue);
    }
    else if(data.fuelType == "PETROL"){
      this.selectedValue = '0';
      console.log(this.selectedValue);
    }
  })
  this.isEditing = true;
}
}

