package com.jukti.domain.models;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class CurrencyDomainModel {
    private String name;
    private String code;
    private String countryCode;
    private List<PeriodDomainModel> periodList;

    public CurrencyDomainModel(String name, String code, String countryCode, List<PeriodDomainModel> periodList) {
        this.name = name;
        this.code = code;
        this.countryCode = countryCode;
        this.periodList = periodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<PeriodDomainModel> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<PeriodDomainModel> periodList) {
        this.periodList = periodList;
    }

    @Override
    public String toString() {
        return "CurrencyDomainModel{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", periodList=" + periodList +
                '}';
    }
}
