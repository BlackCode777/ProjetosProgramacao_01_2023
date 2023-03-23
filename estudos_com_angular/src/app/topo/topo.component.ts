import { Component } from '@angular/core';
import { OfertasService } from '../services/ofertas.service';
import { Oferta } from '../models/oferta.model';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-topo',
  templateUrl: './topo.component.html',
  styleUrls: ['./topo.component.css'],
  //providers: [OfertasService]
})
export class TopoComponent {

        public ofertas: Observable<Oferta[]> | any;

        constructor(
                private ofertasService: OfertasService
        ){ }
        ngOnInit(){}

        pesquisa( termoDaPesquisa: string ): void{
                
                this.ofertas = this.ofertasService.pesquisaDeOfertas(termoDaPesquisa)

                this.ofertas.subscribe(
                        (ofertas: Oferta[]) => console.log(ofertas),
                        (erro: any) => console.log("Falha ao conectar API > ",erro.status)
                )

        }
        

}
