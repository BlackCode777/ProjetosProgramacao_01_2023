import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'; 
import { Observable } from 'rxjs';
// import { ArrayOBJ1 } from '../bubble-sort-algorithm/model/arrayOBJ1.model';
import { ArrayOBJ2 } from '../bubble-sort-algorithm/model/arrayOBJ2.model';
import { Pedido } from '../bubble-sort-algorithm/model/arrayOBJ1.model';

@Injectable({
  providedIn: 'root'
})
export class EstudoangularserviceService {

        apiPedido = "http://localhost:3000/pedido"

  constructor(
        private http: HttpClient
  ) { }

  getPedido( ): Observable<Pedido>{
        return this.http.get<Pedido>(this.apiPedido);
  }

  /**
   *   get(url: string): Observable<any> {
    return this.http.get(url);
  }
   */

}
