package com.example.jssport_back_end.controller;
import com.example.jssport_back_end.model.product.Product;
import com.example.jssport_back_end.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService iProductService;
    @GetMapping("/all-product")
    public ResponseEntity<Page<Product>> getAllProduct(@PageableDefault(page = 0, size = 8) Pageable pageable) {
        Page<Product> productPage = iProductService.getAllProduct(pageable);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    @GetMapping("/detail/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable Long productId){
        Product product = iProductService.findById(productId).orElse(null);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
