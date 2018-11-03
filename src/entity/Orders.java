package entity;

import java.util.Date;
import java.util.List;

public class Orders {
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", paytype='" + payType + '\'' +
                ", consignee='" + consignee + '\'' +
                ", sum=" + sum +
                ", state='" + state + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }

    private User user;
    private String payType;
    private String consignee;
    private double sum;
    private String state;
    private Date orderDate;

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }
    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
    private List<OrderLine> orderLineList;
}
