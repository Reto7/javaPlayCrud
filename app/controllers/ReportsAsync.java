package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.core.j.HttpExecutionContext;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

/**
 * Created by user on 12.03.2017.
 */
public class ReportsAsync {

/*
    @Inject HttpExecutionContext ec;

    public ReportsAsync() {
    }

    public CompletionStage<Result> index() {
        return CompletableFuture.supplyAsync(() -> intensiveComputation())
                .thenApply(i -> ok("Got result: " + i));
    }

    private <U> U intensiveComputation() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
