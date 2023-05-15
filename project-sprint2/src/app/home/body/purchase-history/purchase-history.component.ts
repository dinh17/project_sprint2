import { Component, OnInit } from '@angular/core';
import {Orders} from '../../../entity/order/orders';
import {Cart} from '../../../entity/order/cart';
import {OrderService} from '../../../service/cart/order.service';
import {TokenStorageService} from '../../../service/security/token-storage.service';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {



  orderPage: any;
  orderList: Orders[] = [];
  cartList: Cart[] = [];
  page = 0;

  constructor(private orderService: OrderService,
              private tokenStorageService: TokenStorageService,
  ) {
  }

  ngOnInit(): void {
    this.getOrderPurchase(this.page);
    window.scrollTo(0, 10);
  }

  getPurchaseHistory(idOrder: number) {
    this.orderService.getAllPurchaseHistory(idOrder).subscribe(next => {
      this.cartList = next;
    });
  }

  getOrderPurchase(page: number) {
    // tslint:disable-next-line:radix
    const id = parseInt(this.tokenStorageService.getIdAccount());
    console.log(id);
    this.orderService.findOrderPurchaseByAccountId(id, page).subscribe(next => {
      if (next) {
        this.orderList = next.content;
        console.log(this.orderList);
        this.orderPage = next;
        console.log(this.orderPage);
      }
    });
  }
}
