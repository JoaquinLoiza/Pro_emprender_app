package proemprender;

public class Component {

    private Integer id;
    private String name;
    private Integer cant;
    private Integer price;

    public Component(Integer id, String name, Integer cant, Integer price) {
        this.id = id;
        this.name = name;
        this.cant = cant;
        this.price = price;
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
