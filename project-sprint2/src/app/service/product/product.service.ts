import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from '../security/token-storage.service';
import {Observable} from 'rxjs';
import {Product} from '../../entity/product/product';

const PRODUCT_API = 'http://localhost:8080/api/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient, private token: TokenStorageService) {
  }

  getAllProduct(page: number): Observable<Product[]> {
    return this.httpClient.get<Product[]>('http://localhost:8080/api/product/all-product' + '?page=' + page);
  }

  getProduct(productId: number): Observable<Product> {
    return this.httpClient.get<Product>(PRODUCT_API + '/detail/' + productId);
  }
}
