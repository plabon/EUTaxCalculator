package com.jukti.data.entity;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class ResponseData {
    private String details;
    private String version;
    private List<Currency> currencies;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
