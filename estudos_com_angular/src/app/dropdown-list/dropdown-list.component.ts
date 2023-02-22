import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Food } from './IFood.model';
import { map } from 'rxjs';
import { FormControl } from '@angular/forms';
import { DropdownServicesService } from '../services/dropdown-services.service';


@Component({
  selector: 'app-dropdown-list',
  styleUrls: ['./dropdown-list.component.css'],
  templateUrl: './dropdown-list.component.html',
})
export class DropdownListComponent {

        foodsFake: Food[] = [
                {id: "1",  name:"Pizzas"},
                {id: "2",  name:"Abacate"},
                {id: "3",  name:"Misto"}
        ]        ;
        foods: Food = new Food()
        itemSelectedOption = []
        listDropDownInput: any = ""
        onKeyUp_EscutaEvento( evento: KeyboardEvent ){  }
        saveFoods(){}      
       constructor(private dropdownServicesService: DropdownServicesService){ }
        getFoods(){
                this.dropdownServicesService.getFoods()
                .subscribe( ( result ) => {
                        this.foodsFake = result
                        //this.foods = result
                        //console.log("AQUI >foods<  ",this.itemSelectedOption)
                })
        }
        ngOnInit(){ 
                this.getFoods()
         }
}
