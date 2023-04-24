import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const AUTH_API = 'http://localhost:8080/api/auth/';
@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  httpOptions: any;
  isLoggedIn = false;
  isLoggedInObservable = new Subject<boolean>();
  isUserObservable = new Subject<any>();

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }


  login(obj: any): Observable<any> {
    return this.http.post(AUTH_API + 'sign-in', {
      username: obj.username,
      password: obj.password,
      avatar : obj.avatar,
    }, this.httpOptions);
  }


  register(obj: any): Observable<any> {
    console.log(obj);
    return this.http.post(AUTH_API + 'sign-up', {
      name: obj.name,
      username: obj.username,
      email: obj.email,
      password: obj.pwGroup.password,
      confirmPassword: obj.pwGroup.confirmPassword
    }, this.httpOptions);
  }

  setIsLoggedIn(user: any, isLoggedIn: boolean) {
    this.isLoggedInObservable.next(isLoggedIn);
    this.isUserObservable.next(user);
  }

  getUserLoggedIn(): Observable<any> {
    return this.isUserObservable.asObservable();
  }

  getIsLoggedIn(): Observable<boolean> {
    return this.isLoggedInObservable.asObservable();
  }

  getInfoByAccountId(accountId: number): Observable<Account> {
    return this.http.get<Account>(AUTH_API + 'info/' + accountId);
  }

  updateAvatar(accountId: number, avatar: string) {
    return this.http.patch(AUTH_API + 'updateAvatar', {accountId, avatar});
  }

  updateInfo(account: Account) {
    return this.http.put(AUTH_API + 'updateInfo', account);
  }
}
