package com.jukti.domain.models;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class PeriodDomainModel {
    String effectiveFrom;
    private List<RateDomainModel> rates;

    public PeriodDomainModel(String effectiveFrom, List<RateDomainModel> rates) {
        this.effectiveFrom = effectiveFrom;
        this.rates = rates;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public List<RateDomainModel> getRates() {
        return rates;
    }

    public void setRates(List<RateDomainModel> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "PeriodDomainModel{" +
                "effectiveFrom='" + effectiveFrom + '\'' +
                ", rates=" + rates +
                '}';
    }
}
