package model;

import java.util.ArrayList;
import java.util.List;

/**
 * MODEL
 */
public class Product {


    // Mocking Data (nur zu Testzwecken)
    // List ist ein Interface!
    private static List<Product> products;
    // statischer Initialisierer, somit gibt es diese Variable nur 1x, egal wieviele male man instanziert
    static {
        products = new ArrayList<Product>();
        products.add(new Product("1111111111111","Paperclips", "description 1"));
        products.add(new Product("2222222222222","Paperclips", "description 2"));
        products.add(new Product("3333333333333","Paperclips", "description 3"));
        products.add(new Product("4444444444444","Paperclips", "description 4"));
        products.add(new Product("5555555555555","Paperclips", "description 5"));
    }

    // Attribute
    public String ean;
    public String name;
    public String description;

    // Constructors
    public Product() {}
    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }
    public String toString() {
        return String.format("%s - %s", ean, name);
    }



    //-----------------------------------
    // DAO Methoden
    //-----------------------------------

    public static List<Product> findAll(){
        return products;
    }

    public static void addProduct(Product newProduct) {
        products.add(newProduct);
    }

    public static Product findProduct(String searchEAN) {
        for (Product product : products) {
            if (product.ean.equals(searchEAN)) {
                return product;
            }
        }
    }
}