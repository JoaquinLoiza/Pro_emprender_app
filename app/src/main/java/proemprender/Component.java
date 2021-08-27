package proemprender;

public class Component {

    private Integer id;
    private String name;
    private Integer cant;
    private Integer price;

    public Component(Integer id, String name, Integer price, Integer cant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cant = cant;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCant() {
        return cant;
    }

    public Integer getPrice() {
        return price;
    }
}
