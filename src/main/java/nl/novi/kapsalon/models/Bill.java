package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "bills")
public class Bill extends BaseModel {

    private Long customerId;

    private Long hairdresserId;

    @ManyToMany
    @JoinTable(name = "bills_treatments",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id", referencedColumnName = "id"))
    private List<Treatment> treatments;

    @ManyToMany
    @JoinTable(name = "bills_products",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;

    private Boolean paid;
}
