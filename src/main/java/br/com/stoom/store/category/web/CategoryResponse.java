package br.com.stoom.store.category.web;

import br.com.stoom.store.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class CategoryResponse {

    private Long id;

    private String name;

    public static CategoryResponse of(Category entity) {
        if (isNull(entity)) {
            return null;
        }

        return CategoryResponse.of(entity.getId(), entity.getName());
    }

}