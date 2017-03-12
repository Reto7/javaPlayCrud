package controllers;

import model.Report;
import java.util.List;
import java.util.concurrent.Callable;
import play.*;
//import play.libs.F.Promise;
import play.libs.F.*;
import play.mvc.*;


/**
 * Created by user on 12.03.2017.
 */
public class Reports extends Controller {
    /*
    Every action method must return a Result
    In order to be asynchronous, we need to return a Promise of a Result (Promise<Result>) instead.
    A promise is a commitment to do or not do something.
    A Promise<Result> will eventually be redeemed (eingeloest) with a value of type Result.
    By giving a Promise<Result> instead of a regular Result, we are able to compute the result without
    blocking anything. Play will then serve this result as soon as the promise is redeemed.
    */

    /*
        public static Result index() {
            Promise<Report> promiseOfKPIReport =
                    play.libs.Akka.future(
                            new Callable<Report>() {
                                public Report call() {
                                    return intensiveKPIReport();
                                }
                            }
                    );
            Promise<Report> promiseOfETAReport =
                    play.libs.Akka.future(
                            new Callable<Report>() {
                                public Report call() {
                                    return intensiveETAReport();
                                }
                            }
                    );
            Promise<List<Report>> promises =
                    Promise.waitAll(promiseOfKPIReport, promiseOfETAReport);
            return async(
                    promises.map(
                            new Function<List<Report>, Result>() {
                                public Result apply(List<Report> reports) {
                                    return ok(report.render(reports));
                                }
                            }
                    )
            );
        }
    public static Report intensiveKPIReport() {
        Report r = new Report("KPI report");
        r.execute();
        return r;
    }
    public static Report intensiveETAReport() {
        Report r = new Report("ETA report");
        r.execute();
        return r;
    }
    */
}