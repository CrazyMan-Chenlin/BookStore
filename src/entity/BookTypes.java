package entity;

import util.ColumnName;
import util.TableName;

/**
 * @author chenlin
 */
@TableName(name="types")
public class BookTypes {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @ColumnName(name="id")
    private int id;
    @ColumnName(name="name")
    private String name;
    @ColumnName(name="descr")
    private String descr;
}
