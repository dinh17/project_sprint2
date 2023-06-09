import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchProductComponent} from './search-product/search-product.component';


const routes: Routes = [
  {path: 'search/:categoryId', component: SearchProductComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
