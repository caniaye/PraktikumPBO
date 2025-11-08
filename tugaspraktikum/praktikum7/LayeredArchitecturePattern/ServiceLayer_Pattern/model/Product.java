package ServiceLayer_Pattern.model;

public class Product {
    private int id;
    private String name;
    private long price;

    public Product(int id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public long getPrice() { return price; }
}
