package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.ProductJDBC;
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
public class ProductsJDBC extends Controller{

    // das NEUE PLAY Framework verlangt hier im Controller KEINE STATISCHEN METHODEN, ergibt sonst Fehler!



    private static Database db;

    @Inject
    public ProductsJDBC(Database db) {
        this.db = db;
    }



    /**
     * LIST
     * @return
     */
    public Result listProducts() {
        List<ProductJDBC> products = new ProductJDBC().findAll(db);
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
        ProductJDBC newProduct = Json.fromJson(json, ProductJDBC.class);
        // zur Produktliste hinzufuegen!
        ProductJDBC.addProduct(newProduct, db);
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
        ProductJDBC modifiedProduct = Json.fromJson(json, ProductJDBC.class);
        // zur Produktliste hinzufuegen!
        ProductJDBC.modifyProduct(modifiedProduct, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(modifiedProduct));
    }

    /**
     * SHOW-PRODUCT
     * @return
     */
    // hier benoetigen wir keinen Request Body, die ID steht in der URL
    public Result showProduct(Integer id)  {
        ProductJDBC product = ProductJDBC.findProductById(id, db);
        // man kann es wieder zurueckgeben, ist aber eine Designfrage
        return ok(Json.toJson(product));
    }


}
