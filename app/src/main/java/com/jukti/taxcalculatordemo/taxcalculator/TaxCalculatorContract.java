package com.jukti.taxcalculatordemo.taxcalculator;

import com.jukti.taxcalculatordemo.base.BasePresenter;
import com.jukti.taxcalculatordemo.base.BaseView;
import com.jukti.taxcalculatordemo.models.CurrencyModel;
import com.jukti.taxcalculatordemo.models.PeriodModel;
import com.jukti.taxcalculatordemo.models.RateModel;

import java.util.Currency;
import java.util.List;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public interface TaxCalculatorContract {

    public interface View extends BaseView<Presenter> {
        void showLoadingProgress(boolean isShow);
        void showErrorMsg();
        void showNoInternetConnection();
        void updateUIWithCurrencyList(List<CurrencyModel> currencies);
        void updateUIWithPeriodList(List<PeriodModel> periodModels);
        void updateUIWithRateList(List<RateModel> rateModels);
        void showResult(String result);
    }
    public interface Presenter extends BasePresenter {
        void getAllTaxes();
        void calculateResult(RateModel rate,String ammountStr);
    }

}
