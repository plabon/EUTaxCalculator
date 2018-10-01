package com.jukti.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jukti.data.AppExecutors;
import com.jukti.data.converter.TaxRateModelConverterImpl;
import com.jukti.data.entity.Currency;
import com.jukti.data.entity.Period;
import com.jukti.data.entity.Rate;
import com.jukti.data.entity.ResponseData;
import com.jukti.domain.interactors.taxes.GetTaxListUseCase;
import com.jukti.domain.repository.TaxRateRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxRateRemoteDataSource {
    private static final String TAG = "TaxRateRemoteDataSource";
    private static volatile TaxRateRemoteDataSource INSTANCE;
    private AppExecutors mAppExecutors;
    private JsonVatApi jsonVatApi;

    // Prevent direct instantiation.
    private TaxRateRemoteDataSource(@NonNull AppExecutors appExecutors,
                                    @NonNull JsonVatApi api) {
        mAppExecutors = appExecutors;
        jsonVatApi = api;
    }

    public static TaxRateRemoteDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                      @NonNull JsonVatApi api) {
        if (INSTANCE == null) {
            synchronized (TaxRateRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaxRateRemoteDataSource(appExecutors, api);
                }
            }
        }
        return INSTANCE;
    }

    public void getTaxRates(GetTaxListUseCase.TaxRateListRequest requestValues, final TaxRateRepository.GetTaxRatesCallback callback) {
        jsonVatApi.getCurrencyData().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ResponseData responseData = parseJson(response);
                    callback.onFetchTaxComplete(new TaxRateModelConverterImpl().modelListToDomainListCurrency(responseData.getCurrencies()));
                } catch (IOException e) {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public List<Period> getPeriodListFromJsonArray(JSONArray jsonArray) {
        List<Period> periodList = new ArrayList<>();
        try {
            for (int j = 0; j < jsonArray.length(); j++) {
                Period period = new Period();
                JSONObject perioJsonObject = jsonArray.getJSONObject(j);
                period.setEffectiveFrom(perioJsonObject.optString("effective_from", ""));
                period.setRates(getRateListFromJsonObject(perioJsonObject.getJSONObject("rates")));
                periodList.add(period);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return periodList;
    }

    private List<Rate> getRateListFromJsonObject(JSONObject ratesObj) {
        List<Rate> rates = new ArrayList<>();

        try {
            for (Iterator<String> iter = ratesObj.keys(); iter.hasNext(); ) {
                Rate rate = new Rate();
                String key = iter.next();
                rate.setKey(key);
                rate.setValue(ratesObj.getDouble(key));
                rates.add(rate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rates;
    }

    private ResponseData parseJson(Response<ResponseBody> response) throws IOException {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        String responseData = response.body().string();
        Log.e(TAG, "onResponse: " + responseData);
        ResponseData data = new ResponseData();
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            data.setDetails(jsonObject.optString("details", ""));
            data.setVersion(jsonObject.optString("version", ""));
            List<Currency> currencies = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("rates");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObj = jsonArray.getJSONObject(i);
                Currency currency = new Currency();
                currency.setName(jObj.optString("name", ""));
                currency.setCode(jObj.optString("code", ""));
                currency.setCountryCode(jObj.optString("country_code", ""));
                currency.setPeriodList(getPeriodListFromJsonArray(jObj.getJSONArray("periods")));
                currencies.add(currency);
            }
            data.setCurrencies(currencies);
        } catch (JSONException e) {
        }
        return data;
    }


}
