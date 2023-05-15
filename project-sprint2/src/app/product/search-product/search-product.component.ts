import {Component, HostListener, OnInit} from '@angular/core';
import {ProductService} from '../../service/product/product.service';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../../service/product/category.service';
import {TokenStorageService} from '../../service/security/token-storage.service';
import {ViewportScroller} from '@angular/common';
import {Title} from '@angular/platform-browser';
import {ProductDto} from '../../entity/product/product-dto';
import {Cart} from '../../entity/order/cart';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {
  cart: Cart = {productId: 0, price: 0, quantity: 0};
  cartList: Cart[] = [];
  productList: ProductDto[] = [];
  numberPage = 0;
  totalPages = 0;
  size = 4;
  pageYoffSet = 0;
  last: any;
  first: any;
  role = '';
  categoryId = 0;
  productId = 0;
  productName = '';
  categoryName = '';

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private categoryService: CategoryService,
              private tokenStorageService: TokenStorageService,
              private scroll: ViewportScroller,
              private title: Title) {
    this.title.setTitle('Tìm kiếm');
    if (this.tokenStorageService.getRole()) {
      this.role = this.tokenStorageService.getRole()[0];
    }

    this.activatedRoute.paramMap.subscribe(next => {
      // tslint:disable-next-line:radix
      this.categoryId = parseInt(next.get('categoryId') as string);
      // tslint:disable-next-line:no-shadowed-variable
      this.productService.searchProductByCategory(this.size, this.categoryId).subscribe(next => {
        this.productList = next.content;
        this.numberPage = next.number;
        this.size = next.size;
        this.totalPages = next.totalPages;
        this.first = next.first;
        this.last = next.last;
      });
      // tslint:disable-next-line:no-shadowed-variable
      this.categoryService.getCategoryName(this.categoryId).subscribe(next => {
        this.categoryName = next;
        console.log(this.categoryName);
      }, error => {
        if (error.error) {
          this.categoryName = error.error.text;
        }
      });
      // tslint:disable-next-line:triple-equals
      if (this.categoryId == 0) {
        this.categoryName = 'Tất cả sản phẩm';
      }
    });
  }

  ngOnInit(): void {
    window.scrollTo(0, 60);
  }

  getItemProduct(productId: number, productName: string) {
    this.productId = productId;
    this.productName = productName;
  }

  searchProductByCategory(size: number, id: number) {
    this.productService.searchProductByCategory(size, id).subscribe(next => {
      this.productList = next.content;
      this.numberPage = next.number;
      this.size = next.size;
      this.totalPages = next.totalPages;
      this.first = next.first;
      this.last = next.last;
    });
  }

  @HostListener('window:scroll', ['$event']) onScroll() {
    this.pageYoffSet = window.pageYOffset;
  }

  scrollToBlog() {
    this.scroll.scrollToPosition([0, 60]);
  }
}
