package br.com.stoom.store.brand.web;

import br.com.stoom.store.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class BrandResponse {

    private Long id;

    private String name;

    public static BrandResponse of(Brand entity) {
        if (isNull(entity)) {
            return null;
        }

        return BrandResponse.of(entity.getId(), entity.getName());
    }

}