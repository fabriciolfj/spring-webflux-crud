package com.github.fabriciolfj.crud.product.repositories;

import com.github.fabriciolfj.crud.product.entities.Product;
import com.github.fabriciolfj.crud.product.model.ProductSummaryModel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends R2dbcRepository<Product, Long> {

    Mono<Product> findByCode(final String code);

    @Query("""
        Select p.name as product, c.name as category from Product p, Category c
         Where c.id = p.id_category
    """)
    Flux<ProductSummaryModel> getAlProductWithCategory();
}
