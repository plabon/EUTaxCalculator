package com.jukti.taxcalculatordemo.models;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class RateModel {
    private String key;
    private double value;

    public RateModel(String key, double value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
