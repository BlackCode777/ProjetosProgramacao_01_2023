import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class OfertasService {

        public getOfertas(): Array<string>{

                let ofertas = ["Oferta_1", "Oferta_2", "Oferta_3"];
                return ofertas;
                
        }

  constructor() { }
}
