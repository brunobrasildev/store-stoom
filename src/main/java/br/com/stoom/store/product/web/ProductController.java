package br.com.stoom.store.product.web;

import br.com.stoom.store.core.dto.StatusRequest;
import br.com.stoom.store.product.Product;
import br.com.stoom.store.product.ProductRead;
import br.com.stoom.store.core.enums.Status;
import br.com.stoom.store.product.ProductWrite;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRead read;

    private final ProductWrite white;

    @GetMapping
    public Page<ProductResponse> list(Pageable pageable,
                                           @RequestParam(value = "name", required = false) String name) {
        Page<Product> productList = read.list(pageable, name);

        return productList.map(ProductResponse::of);
    }

    @GetMapping(value = "/brand/{brandId}")
    public Page<ProductResponse> listByBrandId(@PathVariable Long brandId, Pageable pageable) {
        Page<Product> productList = read.listByBrandId(pageable, brandId);

        return productList.map(ProductResponse::of);
    }

    @GetMapping(value = "/category/{categoryId}")
    public Page<ProductResponse> listByCategoryId(@PathVariable Long categoryId, Pageable pageable) {
        Page<Product> productList = read.listByCategoryId(pageable, categoryId);

        return productList.map(ProductResponse::of);
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody Product product) throws Exception {
        product = white.create(product);

        return ProductResponse.of(product);
    }

    @PutMapping(value = "/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody Product product) {
        product = white.update(id, product);

        return ProductResponse.of(product);
    }

    @PatchMapping(value = "/{id}")
    public ProductResponse updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) throws Exception {
        if (!Status.exists(request.getStatus())) {
            throw new Exception("Status not found");
        }

        Product product = white.updateStatus(id, Status.valueOf(request.getStatus()));

        return ProductResponse.of(product);
    }

    @GetMapping(value = "/{id}")
    public ProductResponse info(@PathVariable Long id) throws Exception {
        Product product = read.byId(id);

        return ProductResponse.of(product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        white.deleteById(id);
    }

}
