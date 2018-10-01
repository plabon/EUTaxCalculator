package com.jukti.data.converter;
import com.jukti.data.entity.Currency;
import com.jukti.data.entity.Period;
import com.jukti.data.entity.Rate;
import com.jukti.domain.models.CurrencyDomainModel;
import com.jukti.domain.models.PeriodDomainModel;
import com.jukti.domain.models.RateDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxRateModelConverterImpl implements TaxModelConverter {
    @Override
    public CurrencyDomainModel modelToDomainCurrency(Currency entity) {
        return new CurrencyDomainModel(entity.getName(),entity.getCode(),entity.getCountryCode(),modelListToDomainListPeriod(entity.getPeriodList()));
    }

    @Override
    public PeriodDomainModel modelToDomainPeriod(Period entity) {
        return new PeriodDomainModel(entity.getEffectiveFrom(),modelListToDomainListRates(entity.getRates()));
    }

    @Override
    public RateDomainModel modelToDomainRate(Rate entity) {
        return new RateDomainModel(entity.getKey(),entity.getValue());
    }

    @Override
    public List<CurrencyDomainModel> modelListToDomainListCurrency(List<Currency> entityList) {
        List<CurrencyDomainModel> currencyDomainModelList=new ArrayList<>();
        for(Currency currency:entityList){
            currencyDomainModelList.add(modelToDomainCurrency(currency));
        }
        return currencyDomainModelList;
    }

    @Override
    public List<PeriodDomainModel> modelListToDomainListPeriod(List<Period> entityList) {
        List<PeriodDomainModel> periodDomainModelList=new ArrayList<>();
        for(Period p:entityList){
            periodDomainModelList.add(modelToDomainPeriod(p));
        }
        return periodDomainModelList;
    }

    @Override
    public List<RateDomainModel> modelListToDomainListRates(List<Rate> entityList) {
        List<RateDomainModel> rateList=new ArrayList<>();
        for(Rate r:entityList){
            rateList.add(modelToDomainRate(r));
        }
        return rateList;
    }


}
