package br.com.stoom.store.brand;

import br.com.stoom.store.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "BRAND_SEQ")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Brand withId(Long id) {
        this.id = id;
        return this;
    }

    public Brand newStatus(Status status) {
        this.status = status;
        return this;
    }

}