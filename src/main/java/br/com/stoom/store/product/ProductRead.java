package br.com.stoom.store.product;

import br.com.stoom.store.core.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductRead {

    private final ProductRepository productRepository;


    public Page<Product> list(Pageable pageable, String name) {
        Specification<Product> spec = Specification.where(null);

        spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), Status.ACTIVE));

        if (nonNull(name)) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"));
        }

        return productRepository.findAll(spec, pageable);
    }

    public Product byId(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
    }

    public Page<Product> listByBrandId(Pageable pageable, Long brandId) {
        return productRepository.findAllByBrandIdAndStatus(brandId, Status.ACTIVE, pageable);
    }

    public Page<Product> listByCategoryId(Pageable pageable, Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }
}
