import {Component, OnDestroy, OnInit, Pipe, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {Subscription} from 'rxjs';
import {ConfirmService} from '../../services/confirm/confirm.service';
import {MachineService} from '../../services/machine/machine.service';
import {Machine} from '../../models/Machine';
import {Location} from '../../models/Location';
import {EditCreateMachineComponent} from './edit-create-machine/edit-create-machine.component';
import {LocationService} from '../../services/location/location.service';

@Component({
  selector: 'app-machine',
  templateUrl: './machine.component.html',
  styleUrls: ['./machine.component.scss']
})
export class MachineComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['select', 'name', 'totalSpace', 'type', 'description', 'location', 'action'];
  dataSource: MatTableDataSource<Machine>;
  selection = new SelectionModel<Machine>(true, []);
  isDeleteButtonDisabled = true;
  subscriptions$: { [key: string]: Subscription } = {};
  currentlyModifiedMachine: Machine;
  locations: Location[] = [];

  @ViewChild(MatSort, {static: false})
  set sort(sort: MatSort) {
    this.dataSource.sort = sort;
  }

  @ViewChild(MatPaginator, {static: false})
  set paginator(paginator: MatPaginator) {
    this.dataSource.paginator = paginator;
  }

  constructor(private machineService: MachineService,
              private locationService: LocationService,
              private dialog: MatDialog,
              private confirmService: ConfirmService) { }

  ngOnInit() {
    this.subscriptions$.locationServiceFetch = this.locationService.getAllUserLocations(1).subscribe(locations => {
        this.locations = locations;
      },
      error => {
        console.log(error);
      });

    this.subscriptions$.machineServiceFetch = this.machineService.getAllUserMachines(1).subscribe(machines => {
        this.dataSource = new MatTableDataSource<Machine>(machines);
      },
      error => {
        console.log(error);
      });

    this.subscriptions$.selectionChanged = this.selection.changed.subscribe(() => {
      (this.selection.selected.length > 0) ?
        this.isDeleteButtonDisabled = false :
        this.isDeleteButtonDisabled = true;

    });

    this.getInitialLocation(1);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  onNewMachineClick() {
    const dialogRef = this.dialog.open(EditCreateMachineComponent, {
      data: {
        formType: 'NEW MACHINE',
        confirmButton: 'ADD',
        userLocations: this.locations
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefNewMachine = dialogRef.afterClosed().subscribe(machine => {
      if (machine) {
        this.machineService.addNewMachineForUser(machine).subscribe(newMachine => {
          const data = this.dataSource.data;
          data.push(newMachine);
          this.dataSource.data = data;
        });
      }
    });
  }
  onEditMachineClick(machine: Machine) {
    this.currentlyModifiedMachine = machine;
    const dialogRef = this.dialog.open(EditCreateMachineComponent, {
      data: {
        formType: 'EDIT MACHINE',
        confirmButton: 'SAVE',
        machine,
        userLocations: this.locations
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefEditMachine = dialogRef.afterClosed().subscribe(machine => {
      if(machine) {
        const data = this.dataSource.data;
        const index = data.indexOf(this.currentlyModifiedMachine);
        this.machineService.updateMachineForUser(machine).subscribe(updatedMachine => {
          data[index] = updatedMachine;
          this.dataSource.data = data;
        });
      }
    });
  }

  onDeleteMachineClick() {
    this.confirmService.showConfirmWindow('Are you sure you want to delete this machine?').subscribe((shouldBeDeleted) => {
      if (shouldBeDeleted) {
        this.deleteMachine();
      }
    });
  }

  private deleteMachine(): void {
    this.subscriptions$.dialogRefDeleteMachine = this.machineService.deleteUserSelectedMachines(this.selection.selected).subscribe(() => {
      const data = this.dataSource.data;
      this.selection.selected.forEach((machine) => {
        data.splice(data.indexOf(machine), 1);
      });

      this.dataSource.data = data;
      this.selection.clear();
    });
  }

  ngOnDestroy(): void {
    Object.values(this.subscriptions$).forEach((subscription) => subscription.unsubscribe());
  }


  getInitialLocation(locationId: any): string {
    this.locations.forEach((location: Location) => {
      if (location.id === locationId) {
        return location.city + ', ' + location.street + ' ' + location.streetNumber;
      }
    });
    return '';
  }
}
