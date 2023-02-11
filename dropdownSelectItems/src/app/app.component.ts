import { Component } from '@angular/core';
import { Food } from './models/FoodModel.model';
import { DropdownSeviceService } from './dropdown-sevice.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'dropdownSelectItems';

  constructor(
        public dropdownSeviceService: DropdownSeviceService
  ){

  }











}
