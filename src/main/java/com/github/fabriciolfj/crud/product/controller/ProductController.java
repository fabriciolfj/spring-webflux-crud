package com.github.fabriciolfj.crud.product.controller;

import com.github.fabriciolfj.crud.product.dto.ProductDTO;
import com.github.fabriciolfj.crud.product.entities.Product;
import com.github.fabriciolfj.crud.product.mapper.ProductDTOMapper;
import com.github.fabriciolfj.crud.product.model.ProductSummaryModel;
import com.github.fabriciolfj.crud.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody final ProductDTO productDTO) {
        return Mono.just(productDTO)
                .map(ProductDTOMapper::toEntity)
                .doOnNext(p -> log.info("product mappead {}", p))
                .flatMap(product -> productService.persistProduct(product, productDTO.category()));
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> findProduct(@PathVariable final String code) {
        return productService.findByCode(code);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<ProductSummaryModel> getAllProducts() {
        return productService.findAll();
    }
}
