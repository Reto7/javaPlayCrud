package model;

import com.fasterxml.jackson.databind.JsonNode;
import play.api.db.Database;
import play.libs.Json;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * MODEL
 */
public class Product {

    private static Database db;

    @Inject
    public Product(Database db) {
        this.db = db;
    }


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
    public int id;
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
    public Product(int id, String ean, String name, String description) {
        this.id = id;
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
// get connection
        Connection connection = db.getConnection();
        Statement stmt = null;

        // temporaere liste aller produkte
        List<Product> products = new ArrayList<Product>();

        try {
            stmt = connection.createStatement();
            String sql = "select * from product";
            ResultSet rs = stmt.executeQuery(sql);
            // extract data
            while (rs.next()) {
                int id = rs.getInt("id");
                String ean = rs.getString("ean");
                String name = rs.getString("name");
                String description = rs.getString("description");
                // generate new Product
                Product product = new Product(id,ean,name,description);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        return null;
    }
}