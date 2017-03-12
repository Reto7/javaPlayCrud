package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 12.03.2017.
 */
public class ReportTest {
    @Test
    public void testExecute() throws Exception {
        Report r  = new Report("AssetListe");
        r.execute();

    }

}