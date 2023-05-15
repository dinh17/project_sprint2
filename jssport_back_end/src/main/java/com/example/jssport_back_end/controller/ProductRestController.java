package com.example.jssport_back_end.controller;
import com.example.jssport_back_end.dto.IBestProductDto;
import com.example.jssport_back_end.dto.IProductDto;
import com.example.jssport_back_end.dto.ProductDto;
import com.example.jssport_back_end.model.product.Product;
import com.example.jssport_back_end.service.IProductService;
import com.example.jssport_back_end.service.IPurchaseHistoryService;
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

    @Autowired
    private IPurchaseHistoryService iPurchaseHistoryService;

    @GetMapping("/all-product")
    public ResponseEntity<Page<IProductDto>> getAllProduct(@PageableDefault(page = 0, size = 8) Pageable pageable) {
        Page<IProductDto> productPage = iProductService.getAllProduct(pageable);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/bestProduct")
    public ResponseEntity<Page<IBestProductDto>> getBestProduct(@PageableDefault(page=0,size = 4) Pageable pageable){
        Page<IBestProductDto> productDtos = null;
        productDtos = iPurchaseHistoryService.getBestProduct(pageable);
        if(productDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }


    @GetMapping("/detail/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable Long productId){
        Product product = iProductService.findById(productId).orElse(null);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/searchName")
    public ResponseEntity<Page<IProductDto>> searchByName(@RequestParam("productName") String productName, @PageableDefault(page=0,size = 8) Pageable pageable){
        Page<IProductDto> productPage = null;
        if(productName.isEmpty()){
            productPage =iProductService.getAllProduct(pageable);
        }else {
            productPage = iProductService.searchAllProductByName(pageable, productName);
        }
        if(productPage.isEmpty()){
            return new ResponseEntity<>(productPage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productPage,HttpStatus.OK);
    }

    @GetMapping("/searchCategory")
    public ResponseEntity<Page<IProductDto>> searchAllByCategory(@RequestParam("categoryId") Long categoryId,@PageableDefault(size = 4) Pageable pageable){
        Page<IProductDto> productDtoPage = null;
        if(categoryId == 0){
            productDtoPage =iProductService.getAllProduct(pageable);
        }else {
            productDtoPage = iProductService.searchByCategory(categoryId,pageable);
        }
        if(productDtoPage.isEmpty()){
            return new ResponseEntity<>(productDtoPage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productDtoPage,HttpStatus.OK);
    }

}
