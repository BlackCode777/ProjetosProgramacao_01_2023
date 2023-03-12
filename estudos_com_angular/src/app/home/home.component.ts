import { Component } from '@angular/core';
import { OfertasService } from '../services/ofertas.service';
import { Oferta } from '../models/oferta.model';
//import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
  //providers:[OfertasService]
})

export class HomeComponent {

public oferta!: Oferta[];  

constructor( private ofertasService: OfertasService ){}

ngOnInit(){
        this.getMethodObservable()
}

getMethodObservable(){
        this.ofertasService.getOferta()
        .then(  data => {
                this.oferta = data
        })
}



       
        

}

/**
 * 
 *         private resultIsPowerTwo() {
                if (this.isPowerTwo(100) == true) {console.log("Yes" + "<br/>")}
                else { console.log("No" + "<br/>") }
                if (this.isPowerTwo(120) == true) {console.log("Yes" + "<br/>") }
                else {console.log("No" + "<br/>")}
        }
 * 
 *
 isPowerTwo( nPower: number){
        let cnt = 0
        while( nPower > 0 ){
                if( ( nPower & 1) == 1 ){
                        cnt ++;
                }
                nPower = nPower >> 1;
        }
        if( cnt == 1 ){
                return true
        }
        return false
}
 * 
 * 
        public options = [
                { id: 1, name: 'Option 1' },
                { id: 2, name: 'Option 2' },
                { id: 3, name: 'Option 3' },
        ];
        public selectedOption!: number;
        public tableData = [
                { id: 1, name: 'Item 1', description: 'This is item 1' },
                { id: 2, name: 'Item 2', description: 'This is item 2' },
                { id: 3, name: 'Item 3', description: 'This is item 3' },
        ]
 * 
 */