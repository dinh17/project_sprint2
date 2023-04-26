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

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

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
              private orderService: OrderService) {
    this.securityService.getIsLoggedIn().subscribe(next => {
      this.isLoggedIn = next;
    });
    this.securityService.getUserLoggedIn().subscribe(next => {
      this.user = next;
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
  }


  getRole() {
    let role = '';
    if (this.tokenStorageService.getRole()) {
      role = this.tokenStorageService.getRole()[0];
    }
    return role;
  }

  // tslint:disable-next-line:typedef
  @HostListener('window:scroll', ['$event']) onScroll() {
    this.pageYoffSet = window.pageYOffset;
  }

  // tslint:disable-next-line:typedef
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
