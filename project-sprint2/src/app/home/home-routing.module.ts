import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BodyComponent} from './body/body/body.component';
import {CartComponent} from './body/cart/cart.component';
import {DetailComponent} from './body/detail/detail.component';
import {PaymentComponent} from './body/payment/payment.component';
import {CustomerGuard} from '../authguard/customer.guard';
import {IntroduceComponent} from './layout/introduce/introduce.component';
import {PurchaseHistoryComponent} from './body/purchase-history/purchase-history.component';


const routes: Routes = [
  {path: '', component: BodyComponent},
  {path: 'detail/:id', component: DetailComponent},
  {path: 'cart', component: CartComponent},
  {path: 'payment', component: PaymentComponent, canActivate: [CustomerGuard]},
  {path: 'introduce', component: IntroduceComponent},
  {path: 'purchase-history', component: PurchaseHistoryComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
