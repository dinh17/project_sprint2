package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.dto.orders.ICartListDto;
import com.example.jssport_back_end.model.order.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory,Long> {
    @Modifying
    @Query(value = "insert into purchase_history(quantity, order_id, product_id) VALUES (:quantity,:productId,:orderId)", nativeQuery = true)
    void createCartItem(@Param("productId") Long productId, @Param("orderId") Long orderId, @Param("quantity") Integer quantity);

    @Query(value="select ph.product_id as productId,p.product_name as productName ,p.avatar,p.price,ph.quantity  from purchase_history ph join product p on p.product_id = ph.product_id join orders o on ph.order_id = o.order_id where o.payment_status = false and ph.order_id = :orderId",nativeQuery = true)
    List<ICartListDto> getAllProductByOderById(@Param("orderId")Long orderId);
}
