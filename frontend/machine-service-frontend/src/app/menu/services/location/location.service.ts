import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Location} from '../../models/Location';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private httpClient: HttpClient) { }

  public getAllUserLocations(id: number): Observable<Location[]> {
    return this.httpClient.get<Location[]>('http://localhost:8082/configuration/locations/1');
  }
}
