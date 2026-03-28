package com.github.fabriciolfj.crud.product.service;

import com.github.fabriciolfj.crud.product.entities.Category;
import com.github.fabriciolfj.crud.product.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Category> persist(final String category) {
        return getCategoryByName(category)
                .doOnNext(v -> log.info("category founded {}", category))
                .switchIfEmpty(Mono.defer(() -> categoryRepository.save(
                        Category.builder().name(category).build()
                )));
    }

    public Mono<Category> getCategoryByName(final String name) {
        return categoryRepository.findByName(name);
    }
}
