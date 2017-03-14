package controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import model.Report;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by suesstnorma1 on 14.03.17.
 */
@Singleton
public class Reports extends Controller {

    public CompletionStage<Result> listReports() {

        //                hier liste                                                          hier liste
        //                -------------                                                       ---------------
        //CompletionStage<List<Report>> promiseOfReport = CompletableFuture.supplyAsync(() -> loadReports());

        System.out.println("Before start");

        // Promise/Versprechen und Aufruf
        CompletionStage<List<Report>> promiseOfReport =
                CompletableFuture.supplyAsync(() -> loadReports());

        System.out.println("After start");

        // Das Promise wird nach Ausfuehrung (z.B. nach n Sekunden) hier eingeloest
        CompletionStage<Result> promiseOfResult  =
                promiseOfReport.thenApply(r  -> ok(Json.toJson(r)));

        System.out.println("fertig");

        return promiseOfResult;
    }


//    public CompletionStage<Result> kpiReports() {
//
//        CompletionStage<Report> promiseOfKPIReport =
//                CompletableFuture.supplyAsync(
//                        () -> intensiveKPIReport())
//                ;
//
//
//        CompletionStage<Result> promiseOfResult  =
//                promiseOfKPIReport.thenApply(
//                r  -> ok(Json.toJson(r)));
//
//        return promiseOfResult;
//    }

    public static List<Report> loadReports() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Report.reportListe;
    }









}
