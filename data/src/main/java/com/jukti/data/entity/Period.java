package com.jukti.data.entity;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class Period {
    String effectiveFrom;
    private List<Rate> rates;

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
