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

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "bills_products",
//            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"))
//    private List<Bill> bills;

    @OneToMany
    private List<Bill> bills;

}
