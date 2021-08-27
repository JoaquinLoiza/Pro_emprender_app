package proemprender;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private final Integer id;
    private String name;
    private Integer price;
    private ArrayList<Component> component;

    public Product(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.component = new ArrayList<Component>();
    }
    //Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void addComponent(Component comp) {
        this.component.add(comp);
    }

    public ArrayList<Component> getComponent() {
        ArrayList<Component> copia = new ArrayList<Component>();
        copia.addAll(this.component);
        return copia;
    }
}
