package br.com.stoom.store.product;

import br.com.stoom.store.core.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Boolean existsByName(String name);

    @Query(value = "select p.* from product p inner join product_category pc on pc.product_id = p.id where pc.category_id = :categoryId and status = 'ACTIVE'", nativeQuery = true)
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findAllByBrandIdAndStatus(Long brandId, Status active, Pageable pageable);

}