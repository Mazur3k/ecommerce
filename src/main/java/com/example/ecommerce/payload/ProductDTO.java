package com.example.ecommerce.payload;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String name;
    private String description;
    private double discount;
    private String image;
    private String category;
    private int quantity;
    @DecimalMin(value = "0", inclusive = false)
    private double price;
    private double specialPrice;
}
