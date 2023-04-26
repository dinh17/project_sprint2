import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from '../security/token-storage.service';
const PRODUCT_API = 'http://localhost:8080/api/product';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient, private token: TokenStorageService) { }
  searchAllProductByProductName(size: number, productName: string, value: string[]): Observable<any> {
    return this.httpClient.post<any>(PRODUCT_API + '/page1' + '?' + 'size=' + size + '&name=' + productName, value);
  }
}
