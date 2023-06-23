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
@Table(name = "treatments")
public class Treatment extends BaseModel {

    private String name;
    private Integer durationInMinutes;
    private Double price;

    @ManyToMany(mappedBy = "treatments")
    List<Bill> bills;

    public Treatment(String name, int durationInMinutes, double price) {
    }

//    public static class Builder {
//        private String name;
//        private Integer durationInMinutes;
//        private Double price;
//
//        public Builder withName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public Builder withDurationInMinutes(Integer durationInMinutes) {
//            this.durationInMinutes = durationInMinutes;
//            return this;
//        }
//
//        public Builder withPrice(Double price) {
//            this.price = price;
//            return this;
//        }
//
//        public Treatment build() {
//            Treatment treat = new Treatment();
//            treat.setName(this.name);
//            treat.setDurationInMinutes(this.durationInMinutes);
//            treat.setPrice(this.price);
//            return treat;
//        }
//    }
}
