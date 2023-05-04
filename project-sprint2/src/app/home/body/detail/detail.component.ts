import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {Title} from '@angular/platform-browser';
import {SizeService} from '../../../service/size/size.service';
import {WarehouseService} from '../../../service/warehouse/warehouse.service';
import {Product} from '../../../entity/product/product';
import {Size} from '../../../entity/product/size';
import {Warehouse} from '../../../entity/warehouse/warehouse';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  product: Product = {productId: 0, price: 0, productName: '', avatar: ''};
  private productId: number;
  sizeList: Size[] = [];
  role = '';
  warehouse: Warehouse = {id: 0, quantity: 0, productId: 0};

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private title: Title,
              private sizeService: SizeService,
              private warehouseService: WarehouseService) {
    this.sizeService.getALlSize().subscribe(next => {
      this.sizeList = next;
    });
    if (this.tokenStorageService.getToken()) {
      this.role = this.tokenStorageService.getToken()[0];
      console.log('lỗi' + this.role);
    }
    this.activatedRoute.paramMap.subscribe(next => {
      // tslint:disable-next-line:radix
      const id = parseInt(next.get('id') as string);
      // tslint:disable-next-line:no-shadowed-variable
      this.productService.getProduct(id).subscribe(next => {
        this.product = next;
        // tslint:disable-next-line:no-shadowed-variable
        this.warehouseService.findByProductById(next.productId).subscribe(next => {
          this.warehouse = next;
        });
      });
    });
  }

  ngOnInit(): void {
    window.scrollTo(20, 50);
  }

}
