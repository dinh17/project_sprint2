import {Component, OnInit} from '@angular/core';
import {Cart} from '../../../entity/order/cart';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {ShareService} from '../../../service/security/share.service';
import {OrderService} from '../../../service/cart/order.service';
import {Router} from '@angular/router';
import {WarehouseService} from '../../../service/warehouse/warehouse.service';
import {Orders} from '../../../entity/order/orders';
import Swal from 'sweetalert2';
import {Warehouse} from '../../../entity/warehouse/warehouse';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartList: Cart[] = [];
  order: Orders = {orderId: 0, accountId: 0};
  totalPayment = 0;
  length = 0;
  totalQuantity = 0;
  index = 0;
  warehouse: Warehouse = {id: 0, quantity: 0, productId: 0};
  qtyWare = 0;

  constructor(
    private tokenStorageService: TokenStorageService,
    private shareService: ShareService,
    private orderService: OrderService,
    private router: Router,
    private warehouseService: WarehouseService,
  ) {
    this.shareService.getClickEvent().subscribe(next => {
      if (this.tokenStorageService.getToken()) {

        // tslint:disable-next-line:no-shadowed-variable radix
        this.orderService.findOrderByAccountId(parseInt(this.tokenStorageService.getIdAccount())).subscribe(next => {
          this.order = next;
          this.cartList = this.getAllCart(this.order.orderId);
          this.totalPayment = this.getTotalPaymentBE(this.order.orderId);
          this.totalQuantity = this.getTotalQuantityBE(this.order.orderId);
          this.length = this.cartList.length;
        });
        // } else {
        //   this.cartList = this.tokenStorageService.getCart();
        //   this.totalPayment = this.getTotalPayment();
        //   this.length = this.cartList.length;
        //   this.totalQuantity = this.getQuantity();
      }
    });
  }

  getTotalQuantityBE(orderId: number) {
    this.orderService.getTotal(orderId).subscribe(next => {
      if (next) {
        this.totalQuantity = next.totalQuantity;
      }
    });
    return this.totalQuantity;
  }

  getTotalPaymentBE(orderId: number) {
    this.orderService.getTotal(orderId).subscribe(next => {
      if (next) {
        this.totalPayment = next.totalPayment;
      }
    });
    return this.totalPayment;
  }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      // tslint:disable-next-line:radix
      this.orderService.findOrderByAccountId(parseInt(this.tokenStorageService.getIdAccount())).subscribe(next => {
        this.order = next;
        this.cartList = this.getAllCart(this.order.orderId);
        this.totalQuantity = this.getTotalQuantityBE(this.order.orderId);
        this.totalPayment = this.getTotalPaymentBE(this.order.orderId);
      });
    }
    window.scrollTo(0, 10);
  }

  reduceQuantity(productId: number, qty: number, index: number) {
    // tslint:disable-next-line:triple-equals
    if (qty == 0) {
      this.removeCart(productId, this.index);
      return;
    }
    this.orderService.updateQuantity(this.order.orderId, productId, qty).subscribe(next => {
      this.shareService.sendClickEvent();
    });
  }

  increaseQuantity(productId: number, qty: number, index: number) {
    let qty2 = 0;
    this.warehouseService.findByProductById(productId).subscribe(next => {
      qty2 = next.quantity;
      console.log('qty2' + qty2);
      console.log('qty' + qty);
      console.log(productId);
      if (qty > qty2) {
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Thông báo!',
          text: 'Mặt hàng này đã hết',
          showConfirmButton: false,
          timer: 2000
        });
      } else {
        // tslint:disable-next-line:no-shadowed-variable
        this.orderService.updateQuantity(this.order.orderId, productId, qty).subscribe(next => {
          this.shareService.sendClickEvent();
        });
      }
    });
  }

  getWareHouse(productId: number) {
    this.warehouseService.findByProductById(productId).subscribe(next => {
      this.warehouse = next;
      this.qtyWare = next.quantity;
      console.log(next.quantity + ' cái này là next');
      return next.quantity;
    });
    return this.qtyWare;
  }


  updateQuantity(productId: number, qty: number, index: number) {
    if (qty > this.getWareHouse(productId)) {
      debugger
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Thông báo!',
        text: 'Mặt hàng này đã hết',
        showConfirmButton: false,
        timer: 2000
      });
    } else {
      if (this.tokenStorageService.getToken()) {
        this.orderService.updateQuantity(this.order.orderId, productId, qty).subscribe(next => {
          this.shareService.sendClickEvent();
        });
      }
    }
  }


  removeCart(productId: number, index: number) {
    Swal.fire({
      title: 'Bạn có chắc chắn muốn xóa?',
      text: 'Bạn sẽ không thể khôi phục!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Hủy!',
      confirmButtonText: 'Xóa!',
    });
    if (this.tokenStorageService.getToken()) {
      this.orderService.removeCart(productId, this.order.orderId).subscribe(next => {
        this.shareService.sendClickEvent();
      });

      Swal.fire(
        'Đã xóa!',
        'Sản phẩm của bạn đã được xóa khỏi giỏ.',
        'success'
      );
    }
  }

  getAllCart(orderId: number) {
    this.orderService.getAllCart(orderId).subscribe(next => {
      this.cartList = next;
      this.length = next.length;
    });
    return this.cartList;
  }

  moveToPayment() {
    if (this.tokenStorageService.getToken()) {
      this.router.navigateByUrl('/body/payment');
    }
  }
}
