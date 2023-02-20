import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassStyleBindingComponent } from './class-style-binding.component';

describe('ClassStyleBindingComponent', () => {
  let component: ClassStyleBindingComponent;
  let fixture: ComponentFixture<ClassStyleBindingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClassStyleBindingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClassStyleBindingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
