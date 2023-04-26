import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { BodyComponent } from './body/body/body.component';
import { CartComponent } from './body/cart/cart.component';
import { DetailComponent } from './body/detail/detail.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { IntroduceComponent } from './layout/introduce/introduce.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [BodyComponent, CartComponent, DetailComponent, HeaderComponent, FooterComponent, IntroduceComponent],
  exports: [
    HeaderComponent,
    FooterComponent,
    BodyComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
