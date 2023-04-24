import {Component, OnInit} from '@angular/core';
import {Cart} from '../../entity/order/cart';
import {ProductDto} from '../../entity/product/product-dto';
import {FormArray, FormGroup} from '@angular/forms';
import {TokenStorageService} from '../../service/token-storage.service';
import {Title} from '@angular/platform-browser';
import {ActivatedRoute} from '@angular/router';
import {ShareService} from '../../service/share.service';
import {ViewportScroller} from '@angular/common';



@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  cart: Cart = {productId: 0, price: 0, quantity: 0};
  productList: ProductDto[] = [];

  bestPage: any;
  numberPage = 0;
  totalPages = 0;
  size = 4;
  number = 0;
  pageYoffSet = 0;
  last: any;
  first: any;
  role = '';
  productIdDelete = 0;
  productNameDelete = '';
  productName = '';
  productPage: any;
  formPrice: FormGroup;
  search = 0;


  constructor(private productService: ProductService,
              private scroll: ViewportScroller,
              private tokenStorageService: TokenStorageService,
              private shareService: ShareService,
              private activatedRoute: ActivatedRoute,
              private title: Title,
              private warehouseService: WarehouseService) {
    this.title.setTitle('Sports')
    this.formPrice = new FormGroup({
      checkArray: new FormArray([]),
    })
    if(this.tokenStorageService.getRole()){
      this.role = this.tokenStorageService.getRole()[0];
    }
  }

  ngOnInit(): void {
  }

}
