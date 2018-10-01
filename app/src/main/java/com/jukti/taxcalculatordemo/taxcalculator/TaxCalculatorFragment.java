package com.jukti.taxcalculatordemo.taxcalculator;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jukti.taxcalculatordemo.Injection;
import com.jukti.taxcalculatordemo.R;
import com.jukti.taxcalculatordemo.models.CurrencyModel;
import com.jukti.taxcalculatordemo.models.PeriodModel;
import com.jukti.taxcalculatordemo.models.RateModel;
import com.jukti.taxcalculatordemo.utils.Utility;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxCalculatorFragment extends Fragment implements TaxCalculatorContract.View {
    private static final String TAG = "TaxCalculatorFragment";
    private TaxCalculatorPresenter mPresenter;
    private List<CurrencyModel> currencyModels;
    private View rootView;
    private Spinner countrySpinner, dateSpinner;
    private RadioGroup radioGroup;
    private EditText ammountEdittext;
    private TextView resultText;
    private Context mContext;
    private Map<Integer, RadioButton> mapping = new HashMap<>();
    private RateModel selectedRate = null;
    private ProgressDialog dialog;

    public TaxCalculatorFragment() {
        // Required empty public constructor
    }


    public static TaxCalculatorFragment newInstance() {
        TaxCalculatorFragment fragment = new TaxCalculatorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TaxCalculatorPresenter(this, Injection.provideUseCaseHandler(), Injection.provideGetTaxRates(getActivity()));
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Fetching Tax Rates...");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tax_calculator, container, false);
        countrySpinner = (Spinner) rootView.findViewById(R.id.country_spinner);
        dateSpinner = (Spinner) rootView.findViewById(R.id.date_spinner);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.tax_type_rg);
        resultText = (TextView) rootView.findViewById(R.id.result_text);
        ammountEdittext = (EditText) rootView.findViewById(R.id.ammount_et);
        ammountEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPresenter.calculateResult(selectedRate, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(Utility.isNetworkAvailable(getActivity()))
            mPresenter.getAllTaxes();
        else
            showNoInternetConnection();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showLoadingProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

    @Override
    public void showErrorMsg() {

    }

    @Override
    public void showNoInternetConnection() {
        Toast.makeText(mContext,getString(R.string.no_internet_connection_text),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUIWithCurrencyList(List<CurrencyModel> currencies) {
        final ArrayAdapter<CurrencyModel> adapter =
                new ArrayAdapter<CurrencyModel>(mContext, android.R.layout.simple_spinner_dropdown_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CurrencyModel currencyModel = adapter.getItem(i);
                updateUIWithPeriodList(currencyModel.getPeriodList());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void updateUIWithPeriodList(final List<PeriodModel> periodModels) {
        final ArrayAdapter<PeriodModel> periodAdapter =
                new ArrayAdapter<PeriodModel>(mContext, android.R.layout.simple_spinner_dropdown_item, periodModels);
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(periodAdapter);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PeriodModel periodModel = periodAdapter.getItem(i);
                updateUIWithRateList(periodModel.getRates());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void updateUIWithRateList(List<RateModel> rateModels) {
        radioGroup.removeAllViews();
        radioGroup.setOnCheckedChangeListener(null);
        mapping.clear();
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                selectedRate = (RateModel) ((RadioButton) mapping.get(checkedId)).getTag();
                mPresenter.calculateResult(selectedRate, ammountEdittext.getText().toString());

            }
        });
        for (int i = 0; i < rateModels.size(); i++) {
            RateModel rate = rateModels.get(i);
            RadioButton rbn = new RadioButton(mContext);
            int radioButtonId = i + 1000;
            rbn.setId(radioButtonId);
            rbn.setText(rate.getKey() + " - " + rate.getValue() + "%");
            rbn.setTag(rate);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            rbn.setLayoutParams(params);
            mapping.put(radioButtonId, rbn);
            if (i == 0) {
                rbn.setChecked(true);
            }
            radioGroup.addView(rbn);


        }

    }

    @Override
    public void showResult(String result) {
        resultText.setText(result);
    }


    @Override
    public void setPresenter(TaxCalculatorContract.Presenter presenter) {

    }
}
