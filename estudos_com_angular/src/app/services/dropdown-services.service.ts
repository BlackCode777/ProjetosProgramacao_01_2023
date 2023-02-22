import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Food } from '../dropdown-list/IFood.model';

@Injectable({
  providedIn: 'root'
})
export class DropdownServicesService {

        /**
         *   Resources
        http://localhost:3000/food
        http://localhost:3000/pedido
        http://localhost:3000/ofertas
         */

        url = "http://localhost:3000"

  constructor( private http: HttpClient ) { }

  getFoods(): Observable<any>{
        return this.http.get<any>(this.url + "/food")
  }


}
