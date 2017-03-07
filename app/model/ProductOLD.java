package model;

import java.util.ArrayList;
import java.util.List;

/**
 * MODEL
 */
public class ProductOLD {


    // Mocking Data (nur zu Testzwecken)
    // List ist ein Interface!
    private static List<ProductOLD> products;
    // statischer Initialisierer, somit gibt es diese Variable nur 1x, egal wieviele male man instanziert
    static {
        products = new ArrayList<ProductOLD>();
        products.add(new ProductOLD("1111111111111","Paperclips", "description 1"));
        products.add(new ProductOLD("2222222222222","Paperclips", "description 2"));
        products.add(new ProductOLD("3333333333333","Paperclips", "description 3"));
        products.add(new ProductOLD("4444444444444","Paperclips", "description 4"));
        products.add(new ProductOLD("5555555555555","Paperclips", "description 5"));
    }

    // Attribute
    public String ean;
    public String name;
    public String description;

    // Constructors
    public ProductOLD() {}
    public ProductOLD(String ean, String name, String description) {
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

    public static List<ProductOLD> findAll(){
        return products;
    }

    public static void addProduct(ProductOLD newProduct) {
        products.add(newProduct);
    }

    public static ProductOLD findProduct(String searchEAN) {
        for (ProductOLD product : products) {
            if (product.ean.equals(searchEAN)) {
                return product;
            }
        }
        return null;
    }
}