package order;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public Order(){
    }

    public Order(String[] color) {
        this.firstName = "Игуана";
        this.lastName = "Александровна";
        this.address = "Жестянщиков, 34";
        this.metroStation = "Спортивная";
        this.phone = "89999997765";
        this.rentTime = 2;
        this.deliveryDate = "2022-03-11";
        this.comment = "i need to sleep";
        this.color = color;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMetroStation() {
        return metroStation;
    }
    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getRentTime() {
        return rentTime;
    }
    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String[] getColor() {
        return color;
    }
    public void setColor(String[] color) {
        this.color = color;
    }
}
