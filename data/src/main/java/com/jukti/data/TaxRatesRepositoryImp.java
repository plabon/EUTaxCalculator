package com.jukti.data;

import android.support.annotation.NonNull;

import com.jukti.data.remote.TaxRateRemoteDataSource;
import com.jukti.domain.interactors.taxes.GetTaxListUseCase;
import com.jukti.domain.repository.TaxRateRepository;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxRatesRepositoryImp implements TaxRateRepository {
    private final TaxRateRemoteDataSource taxRateRemoteDataSource;
    private static TaxRatesRepositoryImp instance;

    public TaxRatesRepositoryImp(@NonNull TaxRateRemoteDataSource remoteDataSource) {
        this.taxRateRemoteDataSource = remoteDataSource;
    }

    public static TaxRatesRepositoryImp getInstance(TaxRateRemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new TaxRatesRepositoryImp(remoteDataSource);
        }
        return instance;
    }


    @Override
    public void getAllTaxRates(GetTaxListUseCase.TaxRateListRequest requestValues, GetTaxRatesCallback callback) {
        taxRateRemoteDataSource.getTaxRates(requestValues,callback);
    }
}
