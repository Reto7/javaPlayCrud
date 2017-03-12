package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Product;
import play.api.db.Database;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * CONTROLLER
 */
public class Products extends Controller{

    // das NEUE PLAY Framework verlangt hier im Controller KEINE STATISCHEN METHODEN, ergibt sonst Fehler!



    private static Database db;

    @Inject
    public Products(Database db) {
        this.db = db;
    }



    /**
     * LIST
     * @return
     */
    public Result listProducts() {
        List<Product> products = new Product().findAll(db);
        //
        // JsonNode : Jackson
        // Json: Json Helper aus play.libs
        JsonNode json = Json.toJson(products);

        return ok(json);
    }


    /**
     * DETAILS
     * @param ean
     * @return
     */
    public Result details(String ean) {
        return ok();
    }

    /**
     * NEW-PRODUCT
     * @return
     */
    // hiermit bekommen wir den Request Body!
    @BodyParser.Of(BodyParser.Json.class)
    public Result newProduct()  {
        JsonNode json = request().body().asJson();
        Product newProduct = Json.fromJson(json, Product.class);
        // zur Produktliste hinzufuegen!
        Product.addProduct(newProduct, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(newProduct));
    }

    /**
     * MODIFY-PRODUCT
     * @return
     */
    // hiermit bekommen wir den Request Body!
    @BodyParser.Of(BodyParser.Json.class)
    public Result modifyProduct(Integer id)  {
        JsonNode json = request().body().asJson();
        Product modifiedProduct = Json.fromJson(json, Product.class);
        // zur Produktliste hinzufuegen!
        Product.modifyProduct(modifiedProduct, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(modifiedProduct));
    }

    /**
     * SHOW-PRODUCT
     * @return
     */
    // hier benoetigen wir keinen Request Body, die ID steht in der URL
    public Result showProduct(Integer id)  {
        Product product = Product.findProductById(id, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(product));
    }


}
