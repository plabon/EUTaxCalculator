package com.jukti.data.entity;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class Currency {
    private String name;
    private String code;
    private String countryCode;
    private List<Period> periodList;

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

    public List<Period> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<Period> periodList) {
        this.periodList = periodList;
    }
}
