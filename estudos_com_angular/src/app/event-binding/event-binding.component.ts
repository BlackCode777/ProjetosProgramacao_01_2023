import { Component } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  templateUrl: './event-binding.component.html',
  styleUrls: ['./event-binding.component.css']
})
export class EventBindingComponent {

        botaoClicado(){
               alert("Alerta de mensagem - escutando eventos com property-binding") 
        }

        variavelOuvida: string = ''
        variavelValueSalve: any ;

        saveValue( valor: any ){
                this.variavelValueSalve = valor
        }

        // Passando o evento para os metodos
        onKeyUp_EscutaEvento( evento: KeyboardEvent ){
                //console.log((<HTMLInputElement>evento.target).value)//Para pega o elemento dentro da html    
                this.variavelOuvida =  (<HTMLInputElement>evento.target).value
        }

        constructor(){}
}
