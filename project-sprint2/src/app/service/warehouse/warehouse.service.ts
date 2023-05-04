import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Warehouse} from '../../entity/warehouse/warehouse';

const WAREHOUSE_API = 'http://localhost:8080/api/warehouse';

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  constructor(private httpClient: HttpClient) {
  }

  findByProductById(productId: number): Observable<Warehouse> {
    return this.httpClient.get<Warehouse>(WAREHOUSE_API + '/detail/' + productId);
  }
}
