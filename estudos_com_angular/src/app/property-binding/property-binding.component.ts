import { Component } from '@angular/core';

@Component({
  selector: 'app-property-binding',
  templateUrl: './property-binding.component.html',
  styleUrls: ['./property-binding.component.css']
})
export class PropertyBindingComponent {

        url: string = "www.anderson.com"
        getString: string = " Entendendo string e typescript"
        getBoolean: boolean = true

        imagem = "http://lorempixel.com.br/300/200/?1"

        getResultBoolean(argBoolean: boolean){
                argBoolean = false
                return argBoolean
        }
        getResult(){
                return 777;
        }
        getResultString(getStringArg: string){
                return getStringArg
        }

        constructor(){};
        ngOnOnit(){}
        
}
