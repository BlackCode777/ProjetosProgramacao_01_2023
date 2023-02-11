import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TopoComponent } from './topo/topo.component';
import { HomeComponent } from './home/home.component';
import { RodapeComponent } from './rodape/rodape.component';


import {HttpClientModule} from '@angular/common/http';
import { BubbleSortAlgorithmComponent } from './bubble-sort-algorithm/bubble-sort-algorithm.component';

@NgModule({
  declarations: [
    AppComponent,
    TopoComponent,
    HomeComponent,
    RodapeComponent,
    BubbleSortAlgorithmComponent
  ],
  imports: [        
        HttpClientModule,
        BrowserModule,
        NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
