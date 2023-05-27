package nl.novi.kapsalon.dtos;

import nl.novi.kapsalon.models.Product;
import nl.novi.kapsalon.models.Treatment;

import java.util.List;

public class BillDto {
    public Long id;
    public Long customerId;
    public Long hairdresserId;
    public List<Treatment> treatments;
    public List<Product> products;
    public Boolean paid;
}
