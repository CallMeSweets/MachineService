import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../../models/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  public getAllUserProducts(id: number): Observable<Product[]> {
    return this.httpClient.get<Product[]>('http://localhost:8082/configuration/products/1');
  }

  public addNewProductForUser(product: Product): Observable<Product> {
    return this.httpClient.post<Product>('http://localhost:8082/configuration/products/create', product);
  }

  public updateProductForUser(product: Product): Observable<Product> {
    return this.httpClient.patch<Product>('http://localhost:8082/configuration/products/update', product);
  }

  public deleteUserSelectedProducts(products: Product[]): Observable<Product[]> {
    return this.httpClient.post<Product[]>('http://localhost:8082/configuration/products/delete', products);
  }
}
