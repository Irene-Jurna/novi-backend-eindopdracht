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

    @ManyToMany(mappedBy = "bills")
    private List<Treatment> treatments;

//    @ManyToMany(mappedBy = "bills")
//    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Boolean paid;
}
