package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.ProductJPA;
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
public class ProductsJPA extends Controller{

    // das NEUE PLAY Framework verlangt hier im Controller KEINE STATISCHEN METHODEN, ergibt sonst Fehler!



    private static Database db;

    @Inject
    public ProductsJPA(Database db) {
        this.db = db;
    }



    /**
     * LIST
     * @return
     */
    public Result listProducts() {
        List<ProductJPA> products = new ProductJPA().findAll(db);
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
        ProductJPA newProduct = Json.fromJson(json, ProductJPA.class);
        // zur Produktliste hinzufuegen!
        ProductJPA.addProduct(newProduct, db);
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
        ProductJPA modifiedProduct = Json.fromJson(json, ProductJPA.class);
        // zur Produktliste hinzufuegen!
        ProductJPA.modifyProduct(modifiedProduct, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(modifiedProduct));
    }

    /**
     * SHOW-PRODUCT
     * @return
     */
    // hier benoetigen wir keinen Request Body, die ID steht in der URL
    public Result showProduct(Integer id)  {
        ProductJPA product = ProductJPA.findProductById(id, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(product));
    }


}
