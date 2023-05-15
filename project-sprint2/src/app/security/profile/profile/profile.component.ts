import {Component, Inject, OnInit} from '@angular/core';
import {Orders} from '../../../entity/order/orders';
import {OrderService} from '../../../service/cart/order.service';
import {Cart} from '../../../entity/order/cart';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {ShareService} from '../../../service/security/share.service';
import {SecurityService} from '../../../service/security/security.service';
import {Account} from '../../../entity/account/account';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  // @ts-ignore
    account: Account = {
    // tslint:disable-next-line:radix
    accountId: parseInt(this.tokenStorageService.getIdAccount()), username: '',
    email: '', phoneNumber: '', address: '', name: '', avatar: ''
  };

  constructor(
    private tokenStorageService: TokenStorageService,
    private shareService: ShareService,
    private securityService: SecurityService,
  ) {
    // tslint:disable-next-line:radix
    this.securityService.getInfoByAccountId(parseInt(this.tokenStorageService.getIdAccount())).subscribe(next => {
      // @ts-ignore
      this.account = next;
    });
    this.shareService.getClickEvent().subscribe(next => {
      this.getInfoByAccountId();
    });
  }

  ngOnInit(): void {
    this.getInfoByAccountId();
    window.scrollTo(0, 10);
  }

  private getInfoByAccountId() {
    // tslint:disable-next-line:radix
    const id = parseInt(this.tokenStorageService.getIdAccount());
    this.securityService.getInfoByAccountId(id).subscribe(next => {
      // @ts-ignore
      this.account = next;
    });
  }
}
