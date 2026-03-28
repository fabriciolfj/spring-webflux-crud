package com.github.fabriciolfj.crud.product.mapper;

import com.github.fabriciolfj.crud.product.dto.ProductDTO;
import com.github.fabriciolfj.crud.product.entities.Product;

public class ProductDTOMapper {

    private ProductDTOMapper() { }

    public static Product toEntity(final ProductDTO dto) {
        return Product
                .builder()
                .name(dto.name())
                .code(dto.code())
                .build();
    }
}
