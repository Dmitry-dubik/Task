package model;

public class Address {
    private String AddressLine;
    private String City;
    private String Country;
    private String State;


    public String getAddressLine() {
        return AddressLine;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getState() {
        return State;
    }

    public void setAddressLine(String addressLine) {
        AddressLine = addressLine;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setState(String state) {
        State = state;
    }

    @Override
    public String toString() {
        return
                " " + AddressLine + '\'' +
                ", " + City + '\'' +
                ", " + Country + '\'' +
                ", " + State + '\'' +
                ' ';
    }
}
