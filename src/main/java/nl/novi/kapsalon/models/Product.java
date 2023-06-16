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
@Table(name = "products")
public class Product extends BaseModel {

    private String name;
    private Double purchasePrice;
    private Double price;
    private Integer inStock;

    @ManyToMany(mappedBy = "products")
    private List<Bill> bills;

}
