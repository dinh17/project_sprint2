package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.dto.IProductDto;
import com.example.jssport_back_end.dto.ProductDto;
import com.example.jssport_back_end.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface IProductRepository  extends JpaRepository<Product, Long> {
    @Query(value = "select p.* from  `product` p where p.flag_delete = false",nativeQuery = true)
    Page<Product> getAllProduct(Pageable pageable);

    @Query(value = "select * from product where flag_delete = false and product_id = :productId ", nativeQuery = true)
    Optional<Product> findById(@Param("productId") Long productId);

    @Query(value="select p.* from  `product` p where p.flag_delete = false and product_name like concat('%',:name,'%') order by product_id desc",nativeQuery = true)
    Page<Product> searchAllProductByName(Pageable pageable,@Param("name")String name);
}

