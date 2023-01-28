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

        async ngOnInit(){
                // this.ofertaModel = this.ofertasService.getOfertas()
                // console.log(this.ofertaModel)
                // TODO - melhoria
                await this.asincMethodGetData();
                
        }


        private async asincMethodGetData() {
                try {
                        this.ofertaModel = await this.ofertasService.getOfertas_2();
                } catch (error) {
                        console.error(error);
                }
        }
}
