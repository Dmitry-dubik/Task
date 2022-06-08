package model;


public class Hotel {
    private String Name;
    private String price;

    public String getName() {
        return Name;
    }

    private Address address;
    public void setName(String name) {
        Name = name;
    }


    public void setAddress(model.Address address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public model.Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "Name='" + Name + '\'' +
                ", price=" + price +
                ", address=" + address +
                '}';
    }
}
