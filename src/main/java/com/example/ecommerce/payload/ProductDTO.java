package com.example.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
