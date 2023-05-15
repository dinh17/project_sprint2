import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from '../security/token-storage.service';
import {Category} from '../../entity/product/category';


const CATEGORY_API = 'http://localhost:8080/api/category';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient ) { }

  getAllCategory(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(CATEGORY_API + '/list');
  }

  getCategoryName(categoryId: number): Observable<any> {
    return this.httpClient.get<any>(CATEGORY_API + '/categoryName/' + categoryId);
  }
}
