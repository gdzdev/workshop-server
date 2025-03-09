package org.gdzdev.workshop.backend.infrastructure.mappers;

import org.gdzdev.workshop.backend.application.dto.CartProduct;
import org.gdzdev.workshop.backend.application.dto.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.ProductResponse;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.infrastructure.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    CartProduct toProductItem(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toModel(ProductRequest productRequest);

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    Product toModel(ProductEntity productEntity);

    ProductEntity toEntity(Product product);

    List<ProductResponse> toResponseList(List<Product> products);
}

