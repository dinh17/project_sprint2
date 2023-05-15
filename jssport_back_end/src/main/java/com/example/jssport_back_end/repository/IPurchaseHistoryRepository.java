package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.dto.IBestProductDto;
import com.example.jssport_back_end.dto.orders.ICartListDto;
import com.example.jssport_back_end.dto.orders.IOrderPaymentDto;
import com.example.jssport_back_end.dto.orders.ITotalDto;
import com.example.jssport_back_end.dto.orders.OrderPaymentDto;
import com.example.jssport_back_end.model.order.PurchaseHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    @Modifying
    @Query(value = "insert into purchase_history(order_id,product_id,quantity) VALUES (:orderId,:productId,:quantity)", nativeQuery = true)
    void createCartItem(@Param("orderId") Long orderId, @Param("productId") Long productId, @Param("quantity") Integer quantity);

    @Query(value = "select ph.product_id as productId,p.product_name as productName ,p.avatar,p.price,ph.quantity  from purchase_history ph join product p on p.product_id = ph.product_id join orders o on ph.order_id = o.order_id where o.payment_status = false and ph.order_id = :orderId", nativeQuery = true)
    List<ICartListDto> getAllProductByOderById(@Param("orderId") Long orderId);

    @Query(value = "select sum(quantity) as totalQuantity, sum(quantity*price) as totalPayment from product p join purchase_history ph on p.product_id = ph.product_id join orders o on ph.order_id = o.order_id where o.payment_status = false and ph.order_id = :orderId"
            , nativeQuery = true)
    ITotalDto getTotal(@Param("orderId") Long orderId);


    @Query(value = "select * from purchase_history where order_id = :orderId and product_id = :productId", nativeQuery = true)
    PurchaseHistory findCartItemById(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query(value = "delete from purchase_history where order_id = :orderId and product_id = :productId", nativeQuery = true)
    void deleteCartItem(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Modifying
    @Query(value = "update purchase_history set quantity = :quantity where order_id=:orderId and product_id=:productId", nativeQuery = true)
    void updateCartItem(@Param("orderId") Long orderId, @Param("productId") Long productId, @Param("quantity") Integer quantity);

    @Query(value = "select ph.product_id as productId, p.product_name as productName, p.price, p.avatar, ph.quantity from purchase_history ph join product p on p.product_id = ph.product_id join orders o on ph.order_id = o.order_id where o.payment_status = true and ph.order_id = :orderId"
            , nativeQuery = true)
    List<ICartListDto> getPurchaseHistoriesByOrderId(@Param("orderId") Long orderId);

    @Query(value = "select p.product_id as productId, p.product_name as productName, p.description, p.price, p.avatar, sum(ph.quantity) as total from purchase_history ph join product p on p.product_id = ph.product_id join orders o on ph.order_id = o.order_id where o.payment_status = true group by p.product_id order by total desc,p.product_id desc",nativeQuery = true, countQuery = "select p.product_id as productId, p.product_name as productName, p.description, p.price, p.avatar, sum(ph.quantity) as total from purchase_history ph join product p on p.product_id = ph.product_id join orders o on ph.order_id = o.order_id where o.payment_status = true group by p.product_id order by total desc,p.product_id desc")
    Page<IBestProductDto> getBestProduct(Pageable pageable);
}
