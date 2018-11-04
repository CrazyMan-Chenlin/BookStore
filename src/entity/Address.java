package entity;
import java.util.Date;

public class Address {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getDft() {
        return dft;
    }

    public void setDft(int dft) {
        this.dft = dft;
    }




    public Date getMkTime() {
        return mkTime;
    }

    public void setMkTime(Date mkTime) {
        this.mkTime = mkTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    private String id;
    private String name;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private int zip;
    private int dft;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    private User user;

    private Date mkTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
}
