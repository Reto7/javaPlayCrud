package controllers;

import model.ProductJPA;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
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

    @Transactional(readOnly = true)
    public Result getProducts() {
        List<ProductJPA> productsList = (List<ProductJPA>) jpaApi.em().createQuery("select p from ProductJPA p").getResultList();
        return ok(Json.toJson(productsList));
    }

    @Transactional(readOnly = true)
    public Result getProduct(Integer id) {
        ProductJPA product = (ProductJPA) jpaApi.em().createQuery("select p from ProductJPA p where id = " +id).getResultList();
        return ok(Json.toJson(product));
    }

}
