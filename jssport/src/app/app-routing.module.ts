import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CartComponent} from './cart/cart.component';
import {DetailComponent} from './detail/detail.component';
import {BodyComponent} from './home/body/body.component';
import {LoginComponent} from './login/login/login.component';


const routes: Routes = [
  {
    path: '',
    component: BodyComponent
  },
  {
    path: 'cart',
    component: CartComponent
  }, {
    path: 'detail',
    component: DetailComponent
  }, {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
