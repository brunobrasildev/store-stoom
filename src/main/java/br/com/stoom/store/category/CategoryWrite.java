package br.com.stoom.store.category;

import br.com.stoom.store.core.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CategoryWrite {

    private final CategoryRepository repository;

    public Category updateStatus(Long id, Status status) throws Exception {
        Category category = repository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
        category.newStatus(status);

        return repository.save(category);
    }

}
