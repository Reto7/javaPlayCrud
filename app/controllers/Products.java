package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by rk on 07.03.17.
 */
public class Products extends Controller{

    // das NEUE PLAY Framework verlangt KEINE STATISCHEN METHODEN, ergibt sonst Fehler!
    public Result list() {
        return ok();
    }





}
