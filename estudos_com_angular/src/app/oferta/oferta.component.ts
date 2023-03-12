import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OfertasService } from '../services/ofertas.service';
import { Oferta } from '../models/oferta.model';

@Component({
  selector: 'app-oferta',
  templateUrl: './oferta.component.html',
  styleUrls: ['./oferta.component.css']
})
export class OfertaComponent {

        public oferta: Oferta | any;

        constructor(
                private route: ActivatedRoute,
                private ofertasService: OfertasService
        ){  }

        ngOnOnit(){
                this.ofertasOBJService();
        }

        private ofertasOBJService() {
                this.ofertasService.getOfertaPorId(this.route.snapshot.params['id'])
                        .then((oferta: Oferta) => {
                                this.oferta = oferta;
                        });
        }
}
