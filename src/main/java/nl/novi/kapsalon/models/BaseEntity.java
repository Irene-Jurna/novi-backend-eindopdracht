package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

// Takes care of getters and setters
@Data

// Best practise
@NoArgsConstructor
@AllArgsConstructor
// tutorialspoint.com/jpa/jpa_advanced_mappings.htm
@MappedSuperclass
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Instant created;

    @LastModifiedDate
    private Instant updated;

    @Override
    public boolean isNew() {
        return this.id == null;
    }

}
