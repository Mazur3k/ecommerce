package com.example.ecommerce.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private double discount;
    private String image;
    private String category;
    private int quantity;
    private double price;
    private double specialPrice;
}
