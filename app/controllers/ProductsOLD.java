package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import model.ProductOLD;
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
        List<ProductOLD> products = ProductOLD.findAll();

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
        ProductOLD newProduct = Json.fromJson(json, ProductOLD.class);
        // zur Produktliste hinzufuegen!
        ProductOLD.addProduct(newProduct);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(newProduct));

    }



}
