import { Component } from '@angular/core';

@Component({
  selector: 'app-bubble-sort-algorithm',
  templateUrl: './bubble-sort-algorithm.component.html',
  styleUrls: ['./bubble-sort-algorithm.component.css']
})
export class BubbleSortAlgorithmComponent  {
        callback: any;

        array = ["carro", 12, true, "mulher"]

        constructor(){}

        FunctSort( originalARRAY: any[] ): any[]{

                let len = originalARRAY.length;
                console.log("valores do len >>> ",len)

                for( let i=0; i<len; i++ ){
                        console.log(" no loof for (i)>>>  ",i)
                        for( let j=0; j<len; j++ ){
                                console.log(" no loof for (j)>>>  ",j)
                                
                                if( originalARRAY[j] > originalARRAY[j] + 1 ){

                                        let temp = originalARRAY[j];
                                        originalARRAY[j] = originalARRAY[ j+1 ];
                                        originalARRAY[ j+1 ] = temp;

                                        console.log("valores do temp >>> ",temp)
                                }
                        }
                }

                return originalARRAY;

        }

}
