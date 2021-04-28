package com.project;

import java.io.Serializable;

public class LifeIndicators implements Serializable {
    private int _hunger  = 50; // range: 0[CRITICAL] - 100      (Percents)
    private int _bladder = 50; // range: 0[CRITICAL] - 100      (Percents)
    private int _fun     = 50; // range: 0[CRITICAL] - 100      (Percents)
    private int _energy  = 50; // range: 0[CRITICAL] - 100      (Percents)
    private int _hygiene = 50; // range: 0[CRITICAL] - 100      (Percents)

    private static transient final String PATH_TO_SAVE_PRE = "Data/";
    private static transient final String PATH_TO_SAVE_POST = "/live_indicators.json";


    public static String getPathToSave(String finchName){
        return PATH_TO_SAVE_PRE + finchName + PATH_TO_SAVE_POST;
    }
}
