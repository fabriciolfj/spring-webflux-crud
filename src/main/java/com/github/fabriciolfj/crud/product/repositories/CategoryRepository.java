package com.github.fabriciolfj.crud.product.repositories;

import com.github.fabriciolfj.crud.product.entities.Category;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends R2dbcRepository<Category, Long> {

    Mono<Category> findByName(final String name);
}
