import { Injectable } from '@angular/core';
import { Food } from './models/FoodModel.model';

@Injectable({
  providedIn: 'root'
})
export class DropdownSeviceService {
        
  foods: Food[] = [
        {value: 'steak-0', viewValue: 'Steak'},
        {value: 'pizza-1', viewValue: 'Pizza'},
        {value: 'tacos-2', viewValue: 'Tacos'},
  ]

  constructor() { }



}
