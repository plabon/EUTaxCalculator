package com.jukti.taxcalculatordemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jukti.data.AppExecutors;
import com.jukti.data.TaxRatesRepositoryImp;
import com.jukti.data.remote.TaxRateRemoteDataSource;
import com.jukti.data.remote.TaxRateService;
import com.jukti.domain.interactors.taxes.GetTaxListUseCase;
import com.jukti.domain.repository.TaxRateRepository;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class Injection {

    @NonNull
    public static GetTaxListUseCase provideGetTaxRates(@NonNull Context context) {
        return new GetTaxListUseCase(provideTaxRepository(context));
    }
    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static TaxRateRepository provideTaxRepository(Context context) {
        TaxRateService service = TaxRateService.getInstance();
        return TaxRatesRepositoryImp.getInstance(TaxRateRemoteDataSource.getInstance(new AppExecutors(), service.getGithubApiService()));
    }
}
