import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TopoComponent } from './topo/topo.component';
import { HomeComponent } from './home/home.component';
import { RodapeComponent } from './rodape/rodape.component';


import {HttpClientModule} from '@angular/common/http';
import { BubbleSortAlgorithmComponent } from './bubble-sort-algorithm/bubble-sort-algorithm.component';
import { DropdownListComponent } from './dropdown-list/dropdown-list.component';
import { PropertyBindingComponent } from './property-binding/property-binding.component';
import { ClassStyleBindingComponent } from './class-style-binding/class-style-binding.component';
import { EventBindingComponent } from './event-binding/event-binding.component';

@NgModule({
  declarations: [
    AppComponent,
    TopoComponent,
    HomeComponent,
    RodapeComponent,
    BubbleSortAlgorithmComponent,
    DropdownListComponent,
    PropertyBindingComponent,
    ClassStyleBindingComponent,
    EventBindingComponent
  ],
  imports: [     
        HttpClientModule,
        BrowserModule,
        NgbModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
