package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.dto.IWareHouseDto;
import com.example.jssport_back_end.model.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWareHouseRepository extends JpaRepository<Warehouse,Long> {
    @Query(value = "select id, quantity, product_id as productId from warehouse", nativeQuery = true)
    List<IWareHouseDto> getAll();

    @Query(value = "select id, quantity, product_id as productId from warehouse where product_id=:productId", nativeQuery = true)
    IWareHouseDto findByProductId(@Param("productId") Long productId);
}
