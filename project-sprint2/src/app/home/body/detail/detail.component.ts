import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {Title} from '@angular/platform-browser';
import {SizeService} from '../../../service/size/size.service';
import {WarehouseService} from '../../../service/warehouse/warehouse.service';
import {Product} from '../../../entity/product/product';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  role = '';
  product: Product = {productId: 0, price: 0, productName: '', avatar: ''};

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private title: Title,
              private sizeService: SizeService,
              private warehouseService: WarehouseService) {
    if (this.tokenStorageService.getToken()) {
      this.role = this.tokenStorageService.getRole()[0];
    }
    this.activatedRoute.paramMap.subscribe(next => {
      // tslint:disable-next-line:radix
      const id = parseInt(next.get('id') as string);
      // tslint:disable-next-line:no-shadowed-variable
      this.productService.getProduct(id).subscribe(next => {
        this.product = next;
      });
    });
  }

  ngOnInit(): void {
  }

}
