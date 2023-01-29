import { Component } from '@angular/core';
import { OfertasService } from '../services/ofertas.service';
import { Oferta } from '../models/oferta.model';
//import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers:[OfertasService]
})

export class HomeComponent {

        public oferta!: Oferta[];  

        constructor( private ofertasService: OfertasService ){}

        ngOnInit(){
                // TODO - melhoria
                //this.asincMethodGetData();                
        }

        getMethodObservable(){
                this.ofertasService.getData().subscribe( result => {
                        return this.oferta.concat(result);
                })
        }
        

}
