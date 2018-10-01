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

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public interface DomainToViewModelMapper {
    CurrencyModel domainToModelCurrency(CurrencyDomainModel entity);
    PeriodModel domainToModelPeriod(PeriodDomainModel entity);
    RateModel domainToModelRate(RateDomainModel entity);

    List<CurrencyModel> domainListTomodelListCurrency(List<CurrencyDomainModel> entityList);
    List<PeriodModel> domainListTomodelListPeriod(List<PeriodDomainModel> entityList);
    List<RateModel> domainListTomodelListRates(List<RateDomainModel> entityList);
}
