package entity;

import java.util.Date;
import java.util.List;

/**
 * @author chenlin
 */
public class Books {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private  double price;
    private double currentPrice;
    private  String img;

    public List<Integer> getTypeId() {
        return typeId;
    }

    public void setTypeId(List<Integer> typeId) {
        this.typeId = typeId;
    }

    private List<Integer> typeId;
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currentPrice=" + currentPrice +
                ", img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", rebate='" + rebate + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishdate=" + publishdate +
                ", stock=" + stock +
                '}';
    }

    private String author;

    public double getRebate() {
        return rebate;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    private double rebate;
    private String publisher;
    private Date publishdate;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count; //购买数量
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private int stock;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
