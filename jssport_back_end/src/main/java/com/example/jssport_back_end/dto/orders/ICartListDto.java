package com.example.jssport_back_end.dto.orders;

public interface ICartListDto {
    Long getProductId();
    String getProductName();
    Double getPrice();
    String getAvatar();
    Integer getQuantity();
}
