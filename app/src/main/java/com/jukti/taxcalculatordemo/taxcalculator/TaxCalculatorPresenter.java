package com.jukti.taxcalculatordemo.taxcalculator;

import android.text.TextUtils;
import android.util.Log;

import com.jukti.domain.interactors.taxes.GetTaxListUseCase;
import com.jukti.domain.models.CurrencyDomainModel;
import com.jukti.taxcalculatordemo.UseCaseHandler;
import com.jukti.taxcalculatordemo.mapper.DomainToViewModelMapperImp;
import com.jukti.taxcalculatordemo.models.RateModel;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxCalculatorPresenter implements TaxCalculatorContract.Presenter {
    private static final String TAG = "TaxCalculatorPresenter";
    private TaxCalculatorContract.View view;
    private UseCaseHandler mUseCaseHandler;
    private GetTaxListUseCase mGetTaxListUseCase;

    public TaxCalculatorPresenter(TaxCalculatorContract.View view, UseCaseHandler mUseCaseHandler, GetTaxListUseCase mGetWordListUseCase) {
        this.view = view;
        this.mUseCaseHandler = mUseCaseHandler;
        this.mGetTaxListUseCase = mGetWordListUseCase;
    }


    @Override
    public void start() {

    }

    @Override
    public void getAllTaxes() {
        view.showLoadingProgress(true);
        GetTaxListUseCase.TaxRateListRequest requestValues = new GetTaxListUseCase.TaxRateListRequest("", 1);
        mUseCaseHandler.execute(mGetTaxListUseCase, requestValues, new GetTaxListUseCase.Callback<GetTaxListUseCase.TaxRateListResponse>() {

            @Override
            public void onSuccess(GetTaxListUseCase.TaxRateListResponse returnObject) {
                view.showLoadingProgress(false);
                view.updateUIWithCurrencyList(new DomainToViewModelMapperImp().domainListTomodelListCurrency(returnObject.getmResponses()));
                
            }

            @Override
            public void onError() {
                view.showLoadingProgress(false);
                view.showErrorMsg();
                Log.e(TAG,"Something went wrong!");
            }
        });
    }

    @Override
    public void calculateResult(RateModel rate,String ammountStr) {
        if(!TextUtils.isEmpty(ammountStr) && rate!=null){
            double ammountInput=Double.parseDouble(ammountStr);
            double taxRate = rate.getValue();
            double result = ammountInput + ((ammountInput*taxRate)/100);
            view.showResult(result+"");
        }else{
            view.showResult("N/A");
        }
    }
}
