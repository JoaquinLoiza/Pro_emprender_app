package proemprender;

import java.util.ArrayList;

public class Product {

    private final Integer id;
    private String name;
    //private ArrayList<Component> component;

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    /*public ArrayList<Component> getComponent() {
        return component;
    }

    public void setComponent(ArrayList<Component> component) {
        this.component = component;
    }*/
}
