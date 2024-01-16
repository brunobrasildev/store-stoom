package br.com.stoom.store.product;

import br.com.stoom.store.brand.Brand;
import br.com.stoom.store.category.Category;
import br.com.stoom.store.core.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ")
    private Long id;

    @NotEmpty(message = "SKU required")
    private String sku;

    @NotEmpty(message = "SKU required")
    private String name;

    private String description;

    @Column(name = "cost_price")
    @NotNull(message = "costPrice required")
    private BigDecimal costPrice;

    @Column(name = "sale_price")
    @NotNull(message = "salePrice required")
    private BigDecimal salePrice;

    @Column(name = "promotion_price")
    private BigDecimal promotionPrice;

    @Column(name = "promotion_date_start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime promotionDateStart;

    @Column(name = "promotion_date_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime promotionDateEnd;

    @Column(name = "added_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "addedDate required")
    private LocalDateTime addedDate;

    @NotNull(message = "categories required")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") },
            foreignKey = @ForeignKey(name = "product_category_product_fk"),
            inverseForeignKey = @ForeignKey(name = "product_category_category_fk"))
    private Set<Category> categories;

    @NotNull(message = "brand required")
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Product withId(Long id) {
        this.id = id;
        return this;
    }

    public Product newStatus(Status status) {
        this.status = status;
        return this;
    }

}