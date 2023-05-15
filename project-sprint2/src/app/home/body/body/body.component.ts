import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product/product.service';
import {Product} from '../../../entity/product/product';
import {ProjectJson} from '../../../entity/product/project-json';
import {ProductDto} from '../../../entity/product/product-dto';
import {TokenStorageService} from '../../../service/security/token-storage.service';


@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})

export class BodyComponent implements OnInit {
  product: Product[] = [];
  teamPage!: ProjectJson;
  productName = '';
  message = '';
  page = 0;
  bestProductList1: ProductDto[] = [];
  bestProductList2: ProductDto[] = [];
  bestProductList3: ProductDto[] = [];
  role = '';


  constructor(private productService: ProductService,
              private tokenStorageService: TokenStorageService) {
    if (this.tokenStorageService.getRole()) {
      this.role = this.tokenStorageService.getRole()[0];
    }
  }

  ngOnInit(): void {
    window.scrollTo(0, 10);
    this.getAllProduct(0);
    this.getBestProduct();
  }

  private getAllProduct(page: number) {
    this.productService.getAllProduct(page).subscribe(data => {
      // @ts-ignore
      this.product = data.content;
      // @ts-ignore
      this.teamPage = data;
      console.log(this.product);
    });
  }


  search(page: number) {
    this.productService.search(page, this.productName).subscribe(data => {
      // @ts-ignore
      this.product = data.content;
      // @ts-ignore
      this.teamPage = data;
    }, error => {
      this.message = error.error;
    });
  }

  changePage(page: number) {
    this.getAllProduct(page);
  }

  getBestProduct() {
    this.productService.getBestProduct(0).subscribe(data => {
      if (data) {
        this.bestProductList1 = data.content;
        this.teamPage = data;
      }
    });
    this.productService.getBestProduct(1).subscribe(data => {
      if (data) {
        this.bestProductList2 = data.content;
        this.teamPage = data;
      }
    });
    this.productService.getBestProduct(2).subscribe(data => {
      if (data) {
        this.bestProductList2 = data.content;
        this.teamPage = data;
      }
    });
  }

}
