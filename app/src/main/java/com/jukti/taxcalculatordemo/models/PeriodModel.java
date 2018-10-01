package com.jukti.taxcalculatordemo.models;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class PeriodModel {
    String effectiveFrom;
    private List<RateModel> rates;

    public PeriodModel(String effectiveFrom, List<RateModel> rates) {
        this.effectiveFrom = effectiveFrom;
        this.rates = rates;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public List<RateModel> getRates() {
        return rates;
    }

    public void setRates(List<RateModel> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return effectiveFrom;
    }
}
