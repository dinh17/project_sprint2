import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BodyComponent} from './home/body/body.component';
import {HeaderComponent} from './home/layout/header/header.component';
import {FooterComponent} from './home/layout/footer/footer.component';
import {RouterModule} from '@angular/router';
import {CartComponent} from './cart/cart.component';
import {DetailComponent} from './detail/detail.component';
import {LoginComponent} from './login/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {DatePipe} from '@angular/common';
import {AuthInterceptor} from './service/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    BodyComponent,
    HeaderComponent,
    FooterComponent,
    CartComponent,
    DetailComponent,
    LoginComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
