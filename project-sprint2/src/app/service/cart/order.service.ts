import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Orders} from '../../entity/order/orders';
import {Cart} from '../../entity/order/cart';
import {TotalDto} from '../../entity/order/total-dto';


const ORDER_API = 'http://localhost:8080/api/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  constructor(private httpClient: HttpClient) {
  }

  addToCart(orderId: number, productId: number, quantity: number) {
    return this.httpClient.post(ORDER_API + '/cart', {orderId, productId, quantity});
  }

  updateQuantity(orderId: number, productId: number, quantity: number) {
    return this.httpClient.post(ORDER_API + '/updateQuantity', {orderId, productId, quantity});
  }

  findOrderByAccountId(accountId: number): Observable<Orders> {
    return this.httpClient.get<Orders>(ORDER_API + '/detail/' + accountId);
  }

  getAllCart(orderId: number): Observable<Cart[]> {
    return this.httpClient.get<Cart[]>(ORDER_API + '/list/' + orderId);
  }
  getTotal(orderId: number): Observable<TotalDto> {
    return this.httpClient.get<TotalDto>(ORDER_API + '/total/' + orderId);
  }
  removeCart(productId: number, orderId: number) {
    return this.httpClient.delete(ORDER_API + '/delete' + '?productId=' + productId + '&orderId=' + orderId);
  }

  createCart(accountId: number) {
    return this.httpClient.post(ORDER_API + '/order', {accountId});
  }

  payAll(orderId: number, address: string, phone: string, note: string) {
    const cur = new Date();
    return this.httpClient.put(ORDER_API + '/pay',
      {orderId, orderDate: cur.toLocaleString(), address, phoneNumber: phone, note});
  }

  getAllPurchaseHistory(orderId: number): Observable<Cart[]> {
    return this.httpClient.get<Cart[]>(ORDER_API + '/purchase-history/' + orderId);
  }

  findOrderPurchaseByAccountId(accountId: number, page: number): Observable<any> {
    return this.httpClient.get<any>(ORDER_API + '/order-purchase/' + accountId + '?page=' + page);
  }
}
