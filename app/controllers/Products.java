package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import model.Product;
import play.api.db.Database;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * CONTROLLER
 */
public class Products extends Controller{

    // das NEUE PLAY Framework verlangt hier im Controller KEINE STATISCHEN METHODEN, ergibt sonst Fehler!



    private Database db;

    @Inject
    public Products(Database db) {
        this.db = db;
    }





    public Result list() {
        List<Product> products = Product.findAll();
        //
        // JsonNode : Jackson
        // Json: Json Helper aus play.libs
        JsonNode json = Json.toJson(products);

        return ok(json);
    }


    public Result details(String ean) {
        return ok();
    }

    // hiermit bekommen wir den Request Body!
    @BodyParser.Of(BodyParser.Json.class)
    public Result newProduct() {
        JsonNode json = request().body().asJson();
        Product newProduct = Json.fromJson(json, Product.class);
        // zur Produktliste hinzufuegen!  TODO hier auch JDBC einbauen
        Product.addProduct(newProduct);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(newProduct));

    }



}
