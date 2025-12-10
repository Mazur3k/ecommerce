package com.example.ecommerce.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    private List<ProductDTO> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLastPage;
}
