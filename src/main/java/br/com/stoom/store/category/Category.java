package br.com.stoom.store.category;

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", sequenceName = "CATEGORY_SEQ")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Category withId(Long id) {
        this.id = id;
        return this;
    }

    public Category newStatus(Status status) {
        this.status = status;
        return this;
    }

}