import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Food } from './IFood.model';

@Component({
  selector: 'app-dropdown-list',
  styleUrls: ['./dropdown-list.component.css'],
  templateUrl: './dropdown-list.component.html',
})
export class DropdownListComponent {

                //@Input() model;
                foodsNgModel = []
                foods: any = [
                        {id: '', name: 'Selecione'},
                        {id: '0', name: 'Steak'},
                        {id: '1', name: 'Pizza'},
                        {id: '2', name: 'Tacos'},
                ];

                // [object HTMLSelectElement]

                funct(){
                        let valorDoForEach = this.foods.forEach( (valor: { name: string; }) => {
                                console.log(valor.name)
                        })
                        console.log("Aqui")
                        return valorDoForEach
                }
               

        /**
         * (method) Array<Food>.forEach(callbackfn: (value: Food, index: number, array: Food[]) => void, thisArg?: any): void
         */
        

        variavelOuvida: string = ''
        variavelValueSalve: any ;
        itemDropDownListen: any = "";


                
       constructor( ){}

        ngOnInit(){ 
                this.funct()
        }


}
