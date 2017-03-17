package controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import model.Aktie;
import model.Report;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by user on 17.03.2017.
 *
 * http://localhost:9000/aktienkurs/ABC    -- response delay 5 Sek
 * http://localhost:9000/aktienkurs/NESN   -- response immer sofort
 *
 */
@Singleton
public class AktienKursController extends Controller {

    public CompletionStage<Result> getKursByISIN(String isin) {
        // Promise/Versprechen und Aufruf
        CompletionStage<Aktie> promiseOfReport =
                CompletableFuture.supplyAsync(() -> readAktienkurs(isin) );

        // Das Promise wird nach Ausfuehrung (z.B. nach n Sekunden) hier eingeloest
        CompletionStage<Result> promiseOfResult  =
                promiseOfReport.thenApply(r  -> ok(Json.toJson(r)));

        return promiseOfResult;
    }


    public Aktie readAktienkurs(String aktienname){
        if (!aktienname.equals("NESN"))
        {
            // warten falls nicht Nestle
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Aktie a = new Aktie(aktienname);
        System.out.println(aktienname +": " +a.getKurs());
        return a;
    }

}
