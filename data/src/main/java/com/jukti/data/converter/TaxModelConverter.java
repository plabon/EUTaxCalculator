package com.jukti.data.converter;

import com.jukti.data.entity.Currency;
import com.jukti.data.entity.Period;
import com.jukti.data.entity.Rate;
import com.jukti.domain.models.CurrencyDomainModel;
import com.jukti.domain.models.PeriodDomainModel;
import com.jukti.domain.models.RateDomainModel;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public interface TaxModelConverter {

    CurrencyDomainModel modelToDomainCurrency(Currency entity);
    PeriodDomainModel modelToDomainPeriod(Period entity);
    RateDomainModel modelToDomainRate(Rate entity);

    List<CurrencyDomainModel> modelListToDomainListCurrency(List<Currency> entityList);
    List<PeriodDomainModel> modelListToDomainListPeriod(List<Period> entityList);
    List<RateDomainModel> modelListToDomainListRates(List<Rate> entityList);

}
