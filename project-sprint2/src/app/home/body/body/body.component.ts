import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product/product.service';
import {Product} from '../../../entity/product/product';
import {ProjectJson} from '../../../entity/product/project-json';


@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})

export class BodyComponent implements OnInit {
  product: Product[] = [];
  teamPage!: ProjectJson;
  private productName = '';
  private message = '';
  page = 0;


  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    window.scrollTo(0, 10);
    this.getAllProduct(0);
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

  changePage(page: number) {
    this.getAllProduct(page);
  }

  search(page: number) {
    this.productService.search(page, this.productName).subscribe(data => {
      console.log('mang' + data);
      // @ts-ignore
      this.product = data.content;
      // @ts-ignore
      this.teamPage = data;
      console.log(this.product);
    }, error => {
      this.message = error.error;
    });
  }

}
