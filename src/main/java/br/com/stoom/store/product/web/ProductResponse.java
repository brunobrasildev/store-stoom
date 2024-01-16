package br.com.stoom.store.product.web;

import br.com.stoom.store.brand.web.BrandResponse;
import br.com.stoom.store.category.web.CategoryResponse;
import br.com.stoom.store.product.Product;
import br.com.stoom.store.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class ProductResponse {

    private Long id;

    private String sku;

    private String name;

    private String description;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private BigDecimal promotionPrice;

    private LocalDateTime promotionDateStart;

    private LocalDateTime promotionDateEnd;

    private LocalDateTime addedDate;

    private Set<CategoryResponse> categories;

    private BrandResponse brand;

    private Status status;

    public static ProductResponse of(Product entity) {
        if (isNull(entity)) {
            return null;
        }

        return ProductResponse.of(entity.getId(), entity.getSku(), entity.getName(), entity.getDescription(), entity.getCostPrice(), entity.getSalePrice(),
                entity.getPromotionPrice(), entity.getPromotionDateStart(), entity.getPromotionDateEnd(), entity.getAddedDate(),
                entity.getCategories().stream().map(CategoryResponse::of).collect(Collectors.toSet()), BrandResponse.of(entity.getBrand()), entity.getStatus());
    }

}