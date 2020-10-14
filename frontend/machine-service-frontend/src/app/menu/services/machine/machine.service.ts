import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../../models/Product';
import {Machine} from '../../models/Machine';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(private httpClient: HttpClient) { }

  public getAllUserMachines(id: number): Observable<Machine[]> {
    return this.httpClient.get<Machine[]>('http://localhost:8082/configuration/machines/1');
  }

  public addNewMachineForUser(machine: Machine): Observable<Machine> {
    return this.httpClient.post<Machine>('http://localhost:8082/configuration/machines/create', machine);
  }

  public updateMachineForUser(machine: Machine): Observable<Machine> {
    return this.httpClient.patch<Machine>('http://localhost:8082/configuration/machines/update', machine);
  }

  public deleteUserSelectedMachines(machines: Machine[]): Observable<Machine[]> {
    return this.httpClient.post<Machine[]>('http://localhost:8082/configuration/machines/delete', machines);
  }
}
