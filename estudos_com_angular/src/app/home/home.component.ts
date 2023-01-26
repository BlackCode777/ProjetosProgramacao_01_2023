import { Component } from '@angular/core';
import { OfertasService } from '../services/ofertas.service';
import { Oferta } from '../models/oferta.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers:[OfertasService]
})

export class HomeComponent {

        public ofertaModel!: Oferta[]; 

        constructor(
                private ofertasService: OfertasService
        ){}

        ngOnInit(){
                this.ofertaModel = this.ofertasService.getOfertas()
        }

}
