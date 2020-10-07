import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {SelectionModel} from '@angular/cdk/collections';
import {ErrorStateMatcher, MatDialog, MatDialogConfig, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {LocationService} from '../../services/location/location.service';
import {Location} from '../../models/Location';
import {EditCreateComponent} from './edit-create/edit-create.component';
import {Subscription} from 'rxjs';




@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent  implements OnInit, OnDestroy, AfterViewInit {
  displayedColumns: string[] = ['select', 'city', 'street', 'number', 'description', 'action'];
  dataSource: MatTableDataSource<Location>;
  selection = new SelectionModel<Location>(true, []);

  locationToUpdate: Location;
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
    this.subscriptions$['locationServiceFetch'] = this.locationService.getAllUserLocations(1).subscribe(value => {
        this.dataSource = new MatTableDataSource<Location>(value);
      },
      error => {
        console.log(error);
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
      this.clearSelectionAndBlockDeleteButton() :
      this.selectAndUnblockDelete();

  }

  private selectAndUnblockDelete() {
    this.dataSource.data.forEach(row => this.selection.select(row));
    this.isDeleteButtonDisabled = !this.isDeleteButtonDisabled;
  }


  private clearSelectionAndBlockDeleteButton() {
    this.selection.clear();
    this.isDeleteButtonDisabled = !this.isDeleteButtonDisabled;
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: Location): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    // return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  onNewLocationClick() {
    const dialogRef = this.dialog.open(EditCreateComponent, {
      data: {
        formType: 'NEW LOCATION'
      },
      disableClose: true
    });

    this.subscriptions$['dialogRefNewLocation'] = dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });
  }

  onEditLocationClick(location: Location) {
    this.locationToUpdate = location;
    const dialogRef = this.dialog.open(EditCreateComponent, {
      data: {
        formType: 'EDIT LOCATION',
        location
      },
      disableClose: true
    });

    this.subscriptions$['dialogRefEditLocation'] = dialogRef.afterClosed().subscribe(result => {
      const data = this.dataSource.data;
      const index = data.indexOf(this.locationToUpdate);
      data[index] = result;
      this.dataSource.data = data;
    });
  }

  ngOnDestroy(): void {
    Object.values(this.subscriptions$).forEach((subscription) => subscription.unsubscribe());
  }

}

