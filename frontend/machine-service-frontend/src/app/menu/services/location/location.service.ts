import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Location} from '../../models/Location';
import {HttpClient} from '@angular/common/http';
import {Product} from '../../models/Product';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private httpClient: HttpClient) { }

  public getAllUserLocations(id: number): Observable<Location[]> {
    return this.httpClient.get<Location[]>('http://localhost:8082/configuration/locations/1');
  }

  public addNewLocationForUser(location: Location): Observable<Location> {
    return this.httpClient.post<Location>('http://localhost:8082/configuration/locations/create', location);
  }

  public updateLocationForUser(location: Location): Observable<Location> {
    return this.httpClient.patch<Location>('http://localhost:8082/configuration/locations/update', location);
  }

  public deleteUserLocationByid(id: number): Observable<Product[]> {
    return this.httpClient.get<Product[]>('http://localhost:8082/configuration/locations/delete/${id}');
  }

  public deleteUserSelectedLocations(locations: Location[]) {
    return this.httpClient.post<Location[]>('http://localhost:8082/configuration/locations/delete', locations);
  }
}
