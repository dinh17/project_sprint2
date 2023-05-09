package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.dto.IWareHouseDto;
import com.example.jssport_back_end.model.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface IWareHouseRepository extends JpaRepository<Warehouse, Long> {
    @Query(value = "select id, quantity, product_id as productId from warehouse", nativeQuery = true)
    List<IWareHouseDto> getAll();

    @Query(value = "select id, quantity, product_id as productId from warehouse where product_id=:productId", nativeQuery = true)
    IWareHouseDto findByProductId(@Param("productId") Long productId);

    @Modifying
    @Query(value = " update warehouse w join purchase_history ph on w.product_id = ph.product_id join orders o on o.order_id = ph.order_id " +
            " set w.quantity = w.quantity - ph.quantity " +
            " where w.product_id = ph.product_id " +
            "  and o.order_id = :orderId " +
            "  and o.payment_status = true ", nativeQuery = true)
    void updateProduct(@Param("orderId")Long orderId);


}
