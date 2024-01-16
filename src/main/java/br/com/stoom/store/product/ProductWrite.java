package br.com.stoom.store.product;

import br.com.stoom.store.core.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductWrite {

    private final ProductRepository productRepository;

    public Product create(Product product) throws Exception {
        Boolean productExits = productRepository.existsByName(product.getName());

        if (productExits) {
            throw new Exception("Product exists");
        }

        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        product = product.withId(id);

        return productRepository.save(product);
    }

    public Product updateStatus(Long id, Status status) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        product.newStatus(status);

        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
