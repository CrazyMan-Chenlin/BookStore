package entity;

import util.ColumnName;
import util.TableName;

@TableName(name="user")
public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }

    @ColumnName(name = "username")
    private String username;
    @ColumnName(name="password")
    private String password;
    @ColumnName(name="id")
    private int id;
    @ColumnName(name="phone")
    private String phone;
    @ColumnName(name="email")
    private String email;
}
