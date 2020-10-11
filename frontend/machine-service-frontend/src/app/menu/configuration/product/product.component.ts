import {Component, OnDestroy, OnInit} from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {Product} from '../../models/Product';
import {SelectionModel} from '@angular/cdk/collections';
import {Subscription} from 'rxjs';
import {ProductService} from '../../services/product/product.service';
import {EditCreateProductComponent} from './edit-create-product/edit-create-product.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['select', 'name', 'price', 'weight', 'type', 'action'];
  dataSource: MatTableDataSource<Product>;
  selection = new SelectionModel<Product>(true, []);
  isDeleteButtonDisabled = true;
  subscriptions$: { [key: string]: Subscription } = {};
  currentlyModifiedProduct: Product;

  constructor(private productService: ProductService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.subscriptions$.productServiceFetch = this.productService.getAllUserProducts(1).subscribe(products => {
        this.dataSource = new MatTableDataSource<Product>(products);
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

  onNewProductClick() {
    const dialogRef = this.dialog.open(EditCreateProductComponent, {
      data: {
        formType: 'NEW PRODUCT',
        confirmButton: 'ADD'
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefNewLocation = dialogRef.afterClosed().subscribe(product => {
      if (product) {
        this.productService.addNewProductForUser(product).subscribe(newProduct => {
          const data = this.dataSource.data;
          data.push(newProduct);
          this.dataSource.data = data;
        });
      }
    });
  }

  onEditProductClick(product: Product) {
    this.currentlyModifiedProduct = product;
    const dialogRef = this.dialog.open(EditCreateProductComponent, {
      data: {
        formType: 'EDIT PRODUCT',
        confirmButton: 'SAVE',
        product
      },
      disableClose: true
    });

    this.subscriptions$.dialogRefEditLocation = dialogRef.afterClosed().subscribe(product => {
      const data = this.dataSource.data;
      const index = data.indexOf(this.currentlyModifiedProduct);
      this.productService.updateProductForUser(product).subscribe(updatedProduct => {
        data[index] = updatedProduct;
        this.dataSource.data = data;
      });

    });
  }

  onDeleteProductClick() {
    this.subscriptions$.dialogRefDeleteLocation = this.productService.deleteUserSelectedProducts(this.selection.selected).subscribe(() => {
      const data = this.dataSource.data;
      this.selection.selected.forEach((product) => {
        data.splice(data.indexOf(product), 1);
      });

      this.dataSource.data = data;
      this.selection.clear();
    });
  }

  ngOnDestroy(): void {
    Object.values(this.subscriptions$).forEach((subscription) => subscription.unsubscribe());
  }

}
