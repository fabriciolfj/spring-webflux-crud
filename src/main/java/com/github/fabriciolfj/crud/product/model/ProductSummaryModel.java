package com.github.fabriciolfj.crud.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSummaryModel {

    private String product;
    private String category;
}
