package com.jukti.domain.interactors.taxes;
import com.jukti.domain.interactors.UseCase;
import com.jukti.domain.models.CurrencyDomainModel;
import com.jukti.domain.repository.TaxRateRepository;

import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class GetTaxListUseCase extends UseCase<GetTaxListUseCase.TaxRateListRequest, GetTaxListUseCase.TaxRateListResponse> {


    private TaxRateRepository taxRateRepository;

    public GetTaxListUseCase(TaxRateRepository gRepository) {
        this.taxRateRepository = gRepository;
    }



    @Override
    public void execute(TaxRateListRequest requestValues) {
        taxRateRepository.getAllTaxRates(requestValues, new TaxRateRepository.GetTaxRatesCallback() {
            @Override
            public void onFetchTaxComplete(List<CurrencyDomainModel> taxes) {
                TaxRateListResponse response = new TaxRateListResponse(taxes);
                getmCallback().onSuccess(response);

            }

            @Override
            public void onDataNotAvailable() {

            }

            @Override
            public void onError() {

            }
        });
    }

    public static final class TaxRateListRequest implements UseCase.RequestValues{
        private int pageNumber;
        private String searchKey;


        public TaxRateListRequest(String searchKey,int pageNumber) {
            this.searchKey = searchKey;
            this.pageNumber = pageNumber;
        }

        public String getSearchKey() {
            return searchKey;
        }

        public int getPageNumber() {
            return pageNumber;
        }
    }

    public final class TaxRateListResponse implements UseCase.ResponseValue{

        private final List<CurrencyDomainModel> mResponses;

        public TaxRateListResponse(List<CurrencyDomainModel> taxes) {
            mResponses = taxes;
        }


        public List<CurrencyDomainModel> getmResponses() {
            return mResponses;
        }
    }

}

