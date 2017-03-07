package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import model.Product;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * CONTROLLER
 */
public class ProductsOLD extends Controller{

    // das NEUE PLAY Framework verlangt hier im Controller KEINE STATISCHEN METHODEN, ergibt sonst Fehler!


    public Result list() {
        List<Product> products = Product.findAll();

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
        // zur Produktliste hinzufuegen!
        Product.addProduct(newProduct);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(newProduct));

    }



}
