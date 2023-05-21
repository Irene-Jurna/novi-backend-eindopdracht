package nl.novi.kapsalon.models;

import jakarta.persistence.*;

@Entity
@Table(name="treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer durationInMinutes;
    private Double price;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
