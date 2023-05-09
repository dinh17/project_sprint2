import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {Title} from '@angular/platform-browser';
import {SizeService} from '../../../service/size/size.service';
import {WarehouseService} from '../../../service/warehouse/warehouse.service';
import {Product} from '../../../entity/product/product';
import {Size} from '../../../entity/product/size';
import {Warehouse} from '../../../entity/warehouse/warehouse';
import Swal from 'sweetalert2';
import {Orders} from '../../../entity/order/orders';
import {OrderService} from '../../../service/cart/order.service';
import {ShareService} from '../../../service/security/share.service';
import {Cart} from '../../../entity/order/cart';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  product: Product = {productId: 0, price: 0, productName: '', avatar: ''};
  productId: number;
  role = '';
  warehouse: Warehouse = {id: 0, quantity: 0, productId: 0};
  order: Orders = {orderId: 0, accountId: 0};
  quantity = 1;
  cartList: Cart[] = [];
  sizeList: Size[] = [];

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private orderService: OrderService,
              private title: Title,
              private shareService: ShareService,
              private sizeService: SizeService,
              private warehouseService: WarehouseService) {
    this.title.setTitle('Chi tiết sản phẩm');
    this.sizeService.getALlSize().subscribe(next => {
      this.sizeList = next;
    });
    if (this.tokenStorageService.getToken()) {
      this.role = this.tokenStorageService.getToken()[0];
    }
    this.activatedRoute.paramMap.subscribe(next => {
      // tslint:disable-next-line:radix
      const id = parseInt(next.get('id') as string);
      // tslint:disable-next-line:no-shadowed-variable
      this.productService.getProduct(id).subscribe(next => {
        this.product = next;
        // tslint:disable-next-line:no-shadowed-variable
        this.warehouseService.findByProductById(next.productId).subscribe(next => {
          this.warehouse = next;
        });
      });
    });
    if (this.tokenStorageService.getToken()) {
      // tslint:disable-next-line:radix
      this.orderService.findOrderByAccountId(parseInt(this.tokenStorageService.getIdAccount())).subscribe(next => {
        this.order = next;
        console.log(next);
      });
    }
  }

  checkQuantity(carts: Cart[], productId: number, qty: number) {
    let sum = qty;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < carts.length; i++) {
      // tslint:disable-next-line:triple-equals
      if (carts[i].productId == productId) {
        sum += carts[i].quantity;
      }
    }
    return sum;
    console.log(qty);
  }

  ngOnInit(): void {
    window.scrollTo(20, 50);
  }

  addToCart(productId: number, quantity: string, item: Product) {
    const qty = this.checkQuantity(this.cartList, item.productId, Number(quantity));
    console.log(qty, this.warehouse.quantity);
    if (qty > this.warehouse.quantity) {
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
        // tslint:disable-next-line:radix
        this.orderService.addToCart(this.order.orderId, productId, parseInt(quantity)).subscribe(next => {
          console.log(next);
          this.shareService.sendClickEvent();
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Thông báo!',
            text: 'Đã thêm sản phẩm  vào giỏ hàng.',
            showConfirmButton: false,
            timer: 2000
          });
        });
      }
    }
  }
}
