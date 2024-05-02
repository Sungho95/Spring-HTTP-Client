package rallymate.restclient.product;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String productType;

    public Product(int id, String name, double price, String description, String productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }
}
