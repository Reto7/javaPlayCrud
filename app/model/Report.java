package model;

import play.Logger;

/**
 * Created by user on 12.03.2017.
 */
public class Report {
    String name;
    public Report(String name) {
        this.name = name;
    }
    public void execute() {
        long start = System.currentTimeMillis();
        Logger.info("starting intensive " + name + " report at " + start);
        try {
            // Simulate/Fake long long execution time ...
            Thread.sleep(5000);
        } catch(Exception e) {}
        Logger.info("done with intensive " + name + " report ");
        Logger.info("took "
                + ((System.currentTimeMillis() - start) / 1000) + "s");
    }
    public String toString() {
        return name;
    }
}
