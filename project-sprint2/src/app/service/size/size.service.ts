import {Injectable} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Size} from '../../entity/product/size';

const SIZE_API = 'http://localhost:8080/api/size';

@Injectable({
  providedIn: 'root'
})
export class SizeService {

  constructor(private httpClient: HttpClient) {
  }

  getALlSize(): Observable<Size[]> {
    return this.httpClient.get<Size[]>(SIZE_API + '/list');
  }

}
