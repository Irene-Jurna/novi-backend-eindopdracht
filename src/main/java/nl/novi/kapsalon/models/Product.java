package nl.novi.kapsalon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Product extends BaseModel{

    private String name;
    private Double purchasePrice;
    private Double price;
    private Integer inStock;
}
