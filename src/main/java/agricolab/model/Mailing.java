package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mailing {
    private String city;
    private String department;
    private String address;
    private String details;
    private String neighbourhood;

    public Mailing() {
    }

    public Mailing(@JsonProperty("city") String city,
                   @JsonProperty("department") String department,
                   @JsonProperty("address") String address,
                   @JsonProperty("details") String details,
                   @JsonProperty("neighbourhood") String neighbourhood) {
        this.city = city;
        this.department = department;
        this.address = address;
        this.details = details;
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    @Override
    public String toString() {
        return "Mailing{" +
            "city='" + city + '\'' +
            ", department='" + department + '\'' +
            ", address='" + address + '\'' +
            ", details='" + details + '\'' +
            ", neighbourhood='" + neighbourhood + '\'' +
            '}';
    }
}
