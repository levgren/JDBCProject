package com.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
class Product {
    private int productId;
    private String productName;
    private double price;
    private int shopId;
}
