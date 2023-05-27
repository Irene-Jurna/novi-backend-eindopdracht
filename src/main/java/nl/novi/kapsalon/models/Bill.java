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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Treatment> treatments;

    @ManyToMany(mappedBy = "productsOnBill")
    private List<Product> products;

    private Boolean paid;
}
