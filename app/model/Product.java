package model;

import play.api.db.Database;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MODEL
 */
public class Product {

    // Die Methoden die db benuetzen, duerfen offenbar nicht static sein, sonst:
    // CompletionException: java.lang.NullPointerException


    // List ist ein Interface!
    private static List<Product> products;
    // statischer Initialisierer,
    static {
        products = new ArrayList<Product>();
    }

    // Mocking Data (nur zu Testzwecken)
    // statischer Initialisierer, somit gibt es diese Variable nur 1x, egal wieviele male man instanziert
//    static {
//        products = new ArrayList<Product>();
//        products.add(new Product("1111111111111","Paperclips", "description 1"));
//        products.add(new Product("2222222222222","Paperclips", "description 2"));
//        products.add(new Product("3333333333333","Paperclips", "description 3"));
//        products.add(new Product("4444444444444","Paperclips", "description 4"));
//        products.add(new Product("5555555555555","Paperclips", "description 5"));
//    }

    // Attribute
    public int id;
    public String ean;
    public String name;
    public String description;

    // Constructors
    public Product() {
    }
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

    /**
     * TOSTRING
     */
    public String toString() {
        return String.format("%s - %s", ean, name);
    }


    /**
     * GETTERS/SETTERS
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //-----------------------------------
    // DAO Methoden
    //-----------------------------------

    public static List<Product> findAll(Database db){
        // get connection
        Connection connection = db.getConnection();
        Statement stmt = null;

        // aktuelle liste aller produkte
        List<Product> products = new ArrayList<Product>();

        try {
            stmt = connection.createStatement();
            String sql = "select * from product order by id";
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    /**
     * ADDPRODUCT
     * @param newProduct
     * Achtung! newProduct als Parameter hat noch keine ID!
     * Diese bekommen wir von der DB zurueck
     */
    public static void addProduct(Product newProduct, Database db) {
        // in die DB schreiben
        newProduct.save(db);
        // zur Liste hinzufuegen
        products.add(newProduct);
    }

    /**
     * MODIFYPRODUCT
     * @param modifyedProduct
     * Achtung! newProduct als Parameter hat noch keine ID!
     * Diese bekommen wir von der DB zurueck
     */
    public static void modifyProduct(Product modifyedProduct, Database db) {
        // in die DB schreiben
        modifyedProduct.save(db);
    }

    /**
     * Speichert ein Objekt auf die Datenbank.
     * - wenn ID leer ist, wird ein Insert gemacht
     * - wenn ID vorhanden ist, wird ein Update gemacht
     */
    public void save(Database db)  {
        System.out.println("....saving....");
        // get connection
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        String query = null;
        //
        if (this.id == 0) {
            query = "insert into product (ean,name,description) "
                    + "values (?, ?, ?)";
        } else {
            query = "update product set ean = ?, name = ?, description = ? "
                    + "where id = ?";
        }
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.ean);
            preparedStatement.setString(2, this.name);
            preparedStatement.setString(3, this.description);
            if (id != 0) {
                preparedStatement.setLong(4, this.id);
            }
            System.out.println("save query: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            // falls INSERT, sind wir am Resultat (Primary Key: ID) interessiert
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                int incrementId = rs.getInt(1);
                if (incrementId > 0) {
                    System.out.println("inserted with primary key ID: " +incrementId);
                    this.setId(incrementId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    public static Product findProductByEan(String searchEAN, Database db) {
//        return null;
//    }

    public static Product findProductById(Integer id, Database db) {
        System.out.println("....get by id....");
        // get connection
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        String query = null;
        Product product = null;
        //
        if (id > 0) {
            try {
                query = "select * from product where id = ? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                System.out.println("find query: " + preparedStatement.toString());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    System.out.println("Row selektiert: " +rs.getString("name"));
                    String ean = rs.getString("ean");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    // generate new Product
                    product = new Product(id,ean,name,description);
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return product;
        } // id > 0
        return null;
    }


}