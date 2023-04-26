import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BodyComponent} from './body/body/body.component';
import {CartComponent} from './body/cart/cart.component';
import {DetailComponent} from './body/detail/detail.component';


const routes: Routes = [
  {path: '', component: BodyComponent},
  {path: 'detail/:id', component: DetailComponent},
  {path: 'cart', component: CartComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
