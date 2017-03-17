package model;

import java.util.Random;

/**
 * Created by user on 17.03.2017.
 */
public class Aktie {

    String name;
    int kurs;

    public Aktie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKurs() {
        int minimum = 1;
        int maximum = 16000;
        Random rn = new Random();
        int n = maximum - minimum + 1;
        int i = rn.nextInt() % n;
        int randomNum =  minimum + i;
        setKurs(randomNum);
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }
}
