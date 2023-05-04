import { Component, OnInit } from '@angular/core';
import {Cart} from '../../../entity/order/cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  length = 0;
  cartList: Cart[] = [];


  constructor() { }

  ngOnInit(): void {
    window.scrollTo(0, 10);
  }

}
