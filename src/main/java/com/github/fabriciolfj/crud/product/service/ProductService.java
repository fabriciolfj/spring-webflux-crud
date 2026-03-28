package com.github.fabriciolfj.crud.product.service;

import com.github.fabriciolfj.crud.product.entities.Product;
import com.github.fabriciolfj.crud.product.exceptions.ProductNotFoundException;
import com.github.fabriciolfj.crud.product.model.ProductSummaryModel;
import com.github.fabriciolfj.crud.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Product> persistProduct(final Product product, final String category) {
        return categoryService.persist(category)
                .map(product::addCategory)
                .doOnNext(p -> log.info("product updated category {}", p))
                .flatMap(productRepository::save);
    }

    @Transactional(readOnly = true)
    public Mono<Product> findByCode(final String code) {
        return productRepository.findByCode(code)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new ProductNotFoundException("product not found")))
                );
    }

    @Transactional(readOnly = true)
    public Flux<ProductSummaryModel> findAll() {
        return productRepository.getAlProductWithCategory();
    }
}
