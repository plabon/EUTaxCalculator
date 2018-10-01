package com.jukti.domain.models;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class RateDomainModel {
    private String key;
    private double value;

    public RateDomainModel(String key, double value) {
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

    @Override
    public String toString() {
        return "RateDomainModel{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
