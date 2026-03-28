package com.github.fabriciolfj.crud.product.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private String code;
    private String name;
    private Long idCategory;

    public Product addCategory(final Category category) {
        this.idCategory = category.getId();
        return this;
    }
}
