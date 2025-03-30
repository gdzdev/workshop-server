package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Product toModel(ProductRequest productRequest);

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    Product toModel(ProductEntity productEntity);

    ProductEntity toEntity(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    CartProduct toCartProduct(ProductEntity entity);
}

