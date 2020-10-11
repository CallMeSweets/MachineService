import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {SelectionModel} from '@angular/cdk/collections';
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {LocationService} from '../../services/location/location.service';
import {Location} from '../../models/Location';
import {EditCreateLocationComponent} from './edit-create/edit-create-location.component';
import {Subscription} from 'rxjs';
import {ConfirmDialogWindowComponent} from '../../shared/confirm-dialog-window/confirm-dialog-window.component';




@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent  implements OnInit, OnDestroy, AfterViewInit {
  displayedColumns: string[] = ['select', 'city', 'street', 'number', 'description', 'action'];
  dataSource: MatTableDataSource<Location>;
  selection = new SelectionModel<Location>(true, []);

  currentlyModifiedLocation: Location;
  isDeleteButtonDisabled = true;
  subscriptions$: { [key: string]: Subscription } = {};

  @ViewChild(MatSort, {static: false})
  set sort(sort: MatSort) {
    this.dataSource.sort = sort;
  }

  @ViewChild(MatPaginator, {static: false})
  set paginator(paginator: MatPaginator) {
    this.dataSource.paginator = paginator;
  }

  constructor(private locationService: LocationService,
              private dialog: MatDialog) {
  }


  ngOnInit(): void {
    this.subscriptions$.locationServiceFetch = this.locationService.getAllUserLocations(1).subscribe(value => {
        this.dataSource = new MatTableDataSource<Location>(value);
      },
      error => {
        console.log(error);
      });

    this.selection.changed.subscribe(() => {
      (this.selection.selected.length > 0) ?
        this.isDeleteButtonDisabled = false :
        this.isDeleteButtonDisabled = true;

    });
  }


  ngAfterViewInit(): void {
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

  onNewLocationClick() {
    const dialogRef = this.dialog.open(EditCreateLocationComponent, {
      data: {
        formType: 'NEW LOCATION',
        confirmButton: 'ADD'
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefNewLocation = dialogRef.afterClosed().subscribe(location => {
      if (location) {
        this.locationService.addNewLocationForUser(location).subscribe(newLocation => {
          const data = this.dataSource.data;
          data.push(newLocation);
          this.dataSource.data = data;
        });
      }
    });
  }

  onEditLocationClick(location: Location) {
    this.currentlyModifiedLocation = location;
    const dialogRef = this.dialog.open(EditCreateLocationComponent, {
      data: {
        formType: 'EDIT LOCATION',
        confirmButton: 'SAVE',
        location
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefEditLocation = dialogRef.afterClosed().subscribe(location => {
      const data = this.dataSource.data;
      const index = data.indexOf(this.currentlyModifiedLocation);
      this.locationService.updateLocationForUser(location).subscribe(updatedLocation => {
        data[index] = updatedLocation;
        this.dataSource.data = data;
      });

    });
  }

  onDeleteLocationClick() {
    const dialogRef = this.dialog.open(ConfirmDialogWindowComponent, {
      data: {
        message: 'Are you sure you want to delete this locations?'
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefDeleteConfirmWindow = dialogRef.afterClosed().subscribe((shouldBeDeleted) => {
      if(shouldBeDeleted) {
        this.deleteLocation();
      }
    });
  }

  private deleteLocation(): void{
    this.subscriptions$.dialogRefDeleteLocation = this.locationService.deleteUserSelectedLocations(this.selection.selected).subscribe(() => {
      const data = this.dataSource.data;
      this.selection.selected.forEach((location) => {
        data.splice(data.indexOf(location), 1);
      });

      this.dataSource.data = data;
      this.selection.clear();
    });
  }

  ngOnDestroy(): void {
    Object.values(this.subscriptions$).forEach((subscription) => subscription.unsubscribe());
  }

}

