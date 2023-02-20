import { Component } from '@angular/core';

@Component({
  selector: 'app-dropdown-list',
  styleUrls: ['./dropdown-list.component.css'],
  templateUrl: './dropdown-list.component.html',
})
export class DropdownListComponent {

        /*
        
        [placeholder]=" 'Selecione o item' "
        [settings]="dropdownSettings"
        [data]="dropdownList"
        (onSelect)="onItemSelect($event)"
        (onDeSelect)="onItemDeSelect()"
        [(ngModel)]="selectedItems"
        
        */
        
dropdownSettings: any;
dropdownList: any;
selectedItems = []
        
onItemSelect($event: Event) {
        
        let data = this.getData()
        let selectedItem = data.filter( item => item.item_id == $event)
        let selectedItemGroup = selectedItem[0]['group']
        this.dropdownList = data.map(
                item => {
                        if( item.group == selectedItemGroup){
                                item.isDisabled = false
                        } else {
                                item.isDisabled = true
                        }

                        return item
                }
        )
}

onItemDeSelect() {
        
        if( this.selectedItems && this.selectedItems.length == 0){
                this.dropdownList = this.dropdownList.map((item: { isDisabled: boolean; }) => {
                        item.isDisabled = false
                        return item
                })
        }
}

constructor( ){}

getData(): Array<any> {
        return[
                { item_id: 1,   item_tex:"Apple",   group:"F" },
                { item_id: 2,   item_tex:"Orange",   group:"F" },
                { item_id: 3,   item_tex:"Potatoes",   group:"F" },
                { item_id: 4,   item_tex:"Cabbage",   group:"V" },
                { item_id: 5,   item_tex:"Cauliflower",   group:"V" }
        ]
}

ngOnInit(){
        
        this.dropdownList = this.getData()

}


classificandoParametrosDaFuncao(){
}

}
