package nl.novi.kapsalon.dtos;

public class BillOutputDto {
    public String hairSalon = "Kapsalon De Smidse";
    public Long id;
    public String customerName;
    public String hairdresserName;
    public double totalPrice;

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getHairdresserName() {
        return hairdresserName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setHairdresserName(String hairdresserName) {
        this.hairdresserName = hairdresserName;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
