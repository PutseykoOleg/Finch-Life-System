package com.project;

import java.io.Serializable;

public final class FinchPersonalData implements Serializable {
    private String _name;
    private int _age = 0;  // range: 0 - _MAX-AGE[CRITICAL] (Years)

    private transient final int MAX_AGE = 100;
    private transient final int FINCH_LIFE_DAY_AS_REAL_LIFE_DAYS = 1;

    public FinchPersonalData(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }
}
