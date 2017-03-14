package model;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

/**
 * Created by suesstnorma1 on 14.03.17.
 */

public class Report {


    // Mocking Data (nur zu Testzwecken)
    // List ist ein Interface!
    public static List<Report> reportListe;
    // statischer Initialisierer, somit gibt es diese Variable nur 1x, egal wieviele male man instanziert
    static {
        reportListe = new ArrayList<Report>();
        reportListe.add(new Report("Report 1"));
        reportListe.add(new Report("Report 2"));
        reportListe.add(new Report("Report 3"));
        reportListe.add(new Report("Report 4"));
    }



    private String name;

    public Report(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void execute() {
        long start = System.currentTimeMillis();
        Logger.info("starting intensive " + name + " report at " + start);
        try {
            Thread.sleep(5000);
        } catch(Exception e) {}
        Logger.info("done with intensive " + name + " report at " + System.currentTimeMillis());
        Logger.info("took " + ((System.currentTimeMillis() - start) / 1000) + "s");
    }

    public String toString() {
        return name;
    }
}
