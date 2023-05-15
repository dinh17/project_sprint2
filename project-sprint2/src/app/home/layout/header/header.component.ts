import {Component, HostListener, OnInit} from '@angular/core';
import Swal from 'sweetalert2';
import {Category} from '../../../entity/product/category';
import {Orders} from '../../../entity/order/orders';
import {ViewportScroller} from '@angular/common';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {Cart} from '../../../entity/order/cart';
import {ShareService} from '../../../service/security/share.service';
import {OrderService} from '../../../service/cart/order.service';
import {SecurityService} from '../../../service/security/security.service';
import {Account} from '../../../entity/account/account';
import {ProductService} from '../../../service/product/product.service';
import {Product} from '../../../entity/product/product';
import {ProjectJson} from '../../../entity/product/project-json';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private message: ' ';
  private product: Product[] = [];
  private productPage: any;

  pageYoffSet = 0;
  isLoggedIn = false;
  user: any;
  username = '';
  role = '';
  category: Category[] = [];
  totalQuantity = 0;
  order: Orders = {orderId: 0, accountId: 0};
  cartList: Cart[] = [];
  account: Account = {
    accountId: 0, username: '',
    email: '', phoneNumber: '', address: '', name: '', avatar: ''
  };

  constructor(private scroll: ViewportScroller,
              private tokenStorageService: TokenStorageService,
              private securityService: SecurityService,
              private router: Router,
              private shareService: ShareService,
              private orderService: OrderService,
              private productService: ProductService) {
    this.securityService.getIsLoggedIn().subscribe(next => {
      this.isLoggedIn = next;
    });
    this.securityService.getUserLoggedIn().subscribe(next => {
      this.user = next;
    });

    this.shareService.getClickEvent().subscribe(next => {
      if (this.tokenStorageService.getToken()) {
        // tslint:disable-next-line:radix no-shadowed-variable
        this.orderService.findOrderByAccountId(parseInt(this.tokenStorageService.getIdAccount())).subscribe(next => {
          this.order = next;
          this.totalQuantity = this.getTotalQuantityBE(this.order.orderId);
        });
        this.getInfoByAccountId();
      }
    });
    this.shareService.getClickEvent().subscribe(next => {
      this.cartList = this.tokenStorageService.getCart();
      this.totalQuantity = this.getQuantity();
    });
  }


  getInfoByAccountId() {
    // tslint:disable-next-line:radix
    const id = parseInt(this.tokenStorageService.getIdAccount());
    this.securityService.getInfoByAccountId(id).subscribe(next => {
      // @ts-ignore
      this.account = next;
    });
  }

  ngOnInit(): void {
    this.role = this.getRole();
    this.cartList = this.tokenStorageService.getCart();
    this.shareService.getClickEvent().subscribe(next => {
      this.role = this.getRole();
    });
    if (this.tokenStorageService.getToken()) {
      this.getInfoByAccountId();
      // tslint:disable-next-line:radix
      this.orderService.findOrderByAccountId(parseInt(this.tokenStorageService.getIdAccount())).subscribe(next => {
        this.order = next;
        this.totalQuantity = this.getTotalQuantityBE(this.order.orderId);
      });
    } else {
      this.totalQuantity = this.getQuantity();
    }
  }

  getQuantity() {
    let quantity = 0;
    if (this.cartList != null) {
      this.cartList.forEach((item: any) => {
        quantity += item.quantity;
      });
    }
    return quantity;
  }

  getTotalQuantityBE(orderId: number) {
    this.orderService.getTotal(orderId).subscribe(next => {
      if (next) {
        // this.totalPayment = next.totalPayment;
        this.totalQuantity = next.totalQuantity;
      }
    });
    return this.totalQuantity;
  }

  getRole() {
    let role = '';
    if (this.tokenStorageService.getRole()) {
      role = this.tokenStorageService.getRole()[0];
    }
    return role;
  }

  @HostListener('window:scroll', ['$event']) onScroll() {
    this.pageYoffSet = window.pageYOffset;
  }

  scrollToTop() {
    this.scroll.scrollToPosition([0, 0]);
  }

  scrollToTopLogin() {
    this.scroll.scrollToPosition([0, 660]);
  }


  logout() {
    this.tokenStorageService.logout();
    this.securityService.setIsLoggedIn(null, false);
    this.router.navigateByUrl('/login');
    this.shareService.sendClickEvent();
    Swal.fire({
      position: 'center',
      icon: 'info',
      title: 'Thông báo!',
      text: 'Đăng xuất thành công',
      showConfirmButton: false,
      timer: 2000
    });
  }


}
