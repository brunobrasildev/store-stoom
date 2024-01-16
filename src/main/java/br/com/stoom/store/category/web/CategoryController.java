package br.com.stoom.store.category.web;

import br.com.stoom.store.category.Category;
import br.com.stoom.store.category.CategoryWrite;
import br.com.stoom.store.core.enums.Status;
import br.com.stoom.store.core.dto.StatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryWrite white;

    @PatchMapping(value = "/{id}")
    public CategoryResponse updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) throws Exception {
        if (!Status.exists(request.getStatus())) {
            throw new Exception("Status not found");
        }

        Category category = white.updateStatus(id, Status.valueOf(request.getStatus()));

        return CategoryResponse.of(category);
    }

}
