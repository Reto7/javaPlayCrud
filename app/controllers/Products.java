package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import model.Product;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * CONTROLLER
 */
public class Products extends Controller{

    // das NEUE PLAY Framework verlangt hier im Controller KEINE STATISCHEN METHODEN, ergibt sonst Fehler!


    public Result list() {
        List<Product> products = Product.findAll();

        // JsonNode : Jackson
        // Json: Json Helper aus play.libs
        JsonNode json = Json.toJson(products);

        return ok(json);
    }





}
