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


  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
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

}
