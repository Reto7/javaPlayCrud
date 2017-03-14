package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import model.ProductJPA;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
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

    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public ProductsJPA(FormFactory formFactory, JPAApi jpaApi) {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    public Result index() {
        return ok("OK");
    }

    @Transactional
    public Result addProduct() {
        ProductJPA product = formFactory.form(ProductJPA.class).bindFromRequest().get();
        jpaApi.em().persist(product);
        //jpaApi.em().flush();
        //return redirect(routes.ProductsJPA.index());
        return ok(Json.toJson(product));
    }
    // hiermit bekommen wir den Request Body!
    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result addProduct2() {
        JsonNode json = request().body().asJson();
        ProductJPA product = Json.fromJson(json, ProductJPA.class);
        //ProductJPA product = formFactory.form(ProductJPA.class).bindFromRequest().get();
        jpaApi.em().persist(product);
        //jpaApi.em().flush();
        //return redirect(routes.ProductsJPA.index());
        return ok(Json.toJson(product));
    }


    // TODO das hier funktioniert nicht !
    @Transactional
    public Result modifyProduct(Integer id) {
        System.out.println("MODIFY");
        // die id benoetigen wir hier gar nicht!
        // allenfalls zum Abgleich mit den BODY Daten
        ProductJPA product = formFactory.form(ProductJPA.class).bindFromRequest().get();
        System.out.println("ID:"+product.getId());
        jpaApi.em().persist(product);
        //jpaApi.em().flush();
        //return redirect(routes.ProductsJPA.index());
        return ok(Json.toJson(product));
    }

    @Transactional(readOnly = true)
    public Result getProducts() {
        List<ProductJPA> productsList = (List<ProductJPA>) jpaApi.em().createQuery("select p from ProductJPA p").getResultList();
        return ok(Json.toJson(productsList));
    }

    @Transactional(readOnly = true)
    public Result getProduct(Integer id) {
        List<ProductJPA> productsList = (List<ProductJPA>) jpaApi.em().createQuery("select p from ProductJPA p where id = :id")
                .setParameter("id", id)
                .setMaxResults(10)
                .getResultList(); // TODO hier koennte man auch getSingleResult() verwenden!
        if (productsList.size() == 1 ) {
            return ok(Json.toJson(productsList));
        }  else {
            return ok("dar nur 1 Element sein");
        }
    }

}
