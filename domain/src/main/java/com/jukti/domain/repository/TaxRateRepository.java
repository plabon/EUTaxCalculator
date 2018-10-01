package com.jukti.domain.repository;

import com.jukti.domain.interactors.taxes.GetTaxListUseCase;
import com.jukti.domain.models.CurrencyDomainModel;
import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public interface TaxRateRepository {

        interface GetTaxRatesCallback {

            void onFetchTaxComplete(List<CurrencyDomainModel> taxes);

            void onDataNotAvailable();

            void onError();
        }

        void getAllTaxRates(GetTaxListUseCase.TaxRateListRequest requestValues, GetTaxRatesCallback callback);

}
