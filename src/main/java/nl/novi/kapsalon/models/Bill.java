package nl.novi.kapsalon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="bills")
public class Bill extends BaseModel{

    @OneToMany
    private List<Treatment> treatments;

    @ManyToMany(mappedBy = "productsOnBill")
    private List<Product> products;

    private Boolean paid;
}
