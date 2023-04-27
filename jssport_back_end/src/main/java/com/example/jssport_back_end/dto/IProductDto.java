package com.example.jssport_back_end.dto;

public interface IProductDto {
    Long getProductId();
    String getProductName();
    String getDescription();
    Double getPrice();
    String getAvatar();
    Long getCategoryId();
}
