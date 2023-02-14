import { Component } from '@angular/core';
import { Pedido } from './model/arrayOBJ1.model';
import { TogettherArray } from './model/togettherArray.model';
import { EstudoangularserviceService } from '../services/estudoangularservice.service';

@Component({
  selector: 'app-bubble-sort-algorithm',
  templateUrl: './bubble-sort-algorithm.component.html',
  styleUrls: ['./bubble-sort-algorithm.component.css']
})
export class BubbleSortAlgorithmComponent  {
        url!: string;
        pedido: Pedido = new Pedido() // >>> "http://localhost:3000/pedido"
        acessoArrayTOgetther: TogettherArray = new TogettherArray();        
        constructor(private estudoangularserviceService: EstudoangularserviceService ){ }

        ngOnInit(){
                //this.FunctSort(this.array);
        }

        get(){
                this.estudoangularserviceService.getPedido().subscribe( result => {
                        this.pedido = result
                })
        }

        FunctSort( originalARRAY: any[] ): any[]{
                let len = originalARRAY.length;
                console.log("valores do (len) >>> ",len)
                for( let i=0; i<len; i++ ){
                        console.log(" no loof for (i)>>>  ",i)
                        for( let j=0; j<len; j++ ){
                                console.log(" no loof for (j)>>>  ",j)                                
                                if( originalARRAY[j] > originalARRAY[j] + 1 ){
                                        let temp = originalARRAY[j];
                                        originalARRAY[j] = originalARRAY[ j+1 ];
                                        originalARRAY[ j+1 ] = temp;
                                        console.log("valores do (temp) >>> ",temp)
                                }
                        }
                }
                return originalARRAY;
        }

}
