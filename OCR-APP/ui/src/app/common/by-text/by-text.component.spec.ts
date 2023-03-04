import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ByTextComponent } from './by-text.component';

describe('ByTextComponent', () => {
  let component: ByTextComponent;
  let fixture: ComponentFixture<ByTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ByTextComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ByTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
