package com.jukti.taxcalculatordemo.mapper;

import com.jukti.data.entity.Currency;
import com.jukti.data.entity.Period;
import com.jukti.data.entity.Rate;
import com.jukti.domain.models.CurrencyDomainModel;
import com.jukti.domain.models.PeriodDomainModel;
import com.jukti.domain.models.RateDomainModel;
import com.jukti.taxcalculatordemo.models.CurrencyModel;
import com.jukti.taxcalculatordemo.models.PeriodModel;
import com.jukti.taxcalculatordemo.models.RateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class DomainToViewModelMapperImp implements DomainToViewModelMapper {


    @Override
    public CurrencyModel domainToModelCurrency(CurrencyDomainModel entity) {
        return new CurrencyModel(entity.getName(),entity.getCode(),entity.getCountryCode(),domainListTomodelListPeriod(entity.getPeriodList()));
    }

    @Override
    public PeriodModel domainToModelPeriod(PeriodDomainModel entity) {
        return new PeriodModel(entity.getEffectiveFrom(),domainListTomodelListRates(entity.getRates()));
    }

    @Override
    public RateModel domainToModelRate(RateDomainModel entity) {
        return new RateModel(entity.getKey(),entity.getValue());
    }

    @Override
    public List<CurrencyModel> domainListTomodelListCurrency(List<CurrencyDomainModel> entityList) {
        List<CurrencyModel> currencyModelList=new ArrayList<>();
        for(CurrencyDomainModel currency:entityList){
            currencyModelList.add(domainToModelCurrency(currency));
        }
        return currencyModelList;
    }

    @Override
    public List<PeriodModel> domainListTomodelListPeriod(List<PeriodDomainModel> entityList) {
        List<PeriodModel> periodModelList=new ArrayList<>();
        for(PeriodDomainModel p:entityList){
            periodModelList.add(domainToModelPeriod(p));
        }
        return periodModelList;
    }

    @Override
    public List<RateModel> domainListTomodelListRates(List<RateDomainModel> entityList) {
        List<RateModel> rateList=new ArrayList<>();
        for(RateDomainModel r:entityList){
            rateList.add(domainToModelRate(r));
        }
        return rateList;
    }
}
