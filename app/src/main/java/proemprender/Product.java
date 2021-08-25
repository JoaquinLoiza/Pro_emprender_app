package proemprender;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private final Integer id;
    private String name;
    private Integer price;
    //private ArrayList<Component> component;

    public Product(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
        //this.component = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
