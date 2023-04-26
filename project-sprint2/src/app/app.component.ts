import {Component} from '@angular/core';
import {SecurityService} from './service/security/security.service';
import {ShareService} from './service/security/share.service';
import {TokenStorageService} from './service/security/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'project-sprint2';
  isLoggedIn = false;
  user: any;

  constructor(private tokenStorageService: TokenStorageService,
              private securityService: SecurityService,
              private shareService: ShareService) {

  }

  // tslint:disable-next-line:use-lifecycle-interface
  ngOnInit(): void {
    this.securityService.getIsLoggedIn().subscribe(next => {
      this.isLoggedIn = next;
    });
    this.securityService.getUserLoggedIn().subscribe(next => {
      this.user = next;
    });
    if (this.tokenStorageService.getToken() != null) {
      this.user = this.tokenStorageService.getUser();
      this.securityService.setIsLoggedIn(this.user, true);
    }
    // this.shareService.sendClickEvent();
  }
}
