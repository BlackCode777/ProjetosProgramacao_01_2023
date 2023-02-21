import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Food } from './IFood.model';

@Component({
  selector: 'app-dropdown-list',
  styleUrls: ['./dropdown-list.component.css'],
  templateUrl: './dropdown-list.component.html',
})
export class DropdownListComponent {

        foodsNgModel: Array<Food> = []; 
        foods: any = [
                {id: '', name: 'Selecione'},
                {id: '0', name: 'Steak'},
                {id: '1', name: 'Pizza'},
                {id: '2', name: 'Tacos'},
        ];

        // Nesta variavel existe o valor do dropdown escolhido - como acessar esse valor
        //Selected: any = new HTMLSelectElement()
        listDropDownInput: any 
        // [object HTMLSelectElement]
        returnValueSelectedInDropdownList(){
                this.foods.forEach( (valor: { name: string; }) => {
                        console.log(valor.name)
                        this.listDropDownInput = valor.name
                })
                
        }
      
       constructor( ){}

        ngOnInit(){ 
                this.returnValueSelectedInDropdownList()
        }


}

/**
 *          
        variavelOuvida: string = ''
        variavelValueSalve: any ;
        itemDropDownListen: any = "";

 */