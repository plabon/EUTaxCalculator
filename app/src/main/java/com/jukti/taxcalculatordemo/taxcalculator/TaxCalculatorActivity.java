package com.jukti.taxcalculatordemo.taxcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jukti.taxcalculatordemo.R;
import com.jukti.taxcalculatordemo.utils.ActiivtyUtils;
/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TaxCalculatorFragment taxCalculatorFragment =
                (TaxCalculatorFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (taxCalculatorFragment == null) {
            // Create the fragment
            taxCalculatorFragment = TaxCalculatorFragment.newInstance();
            ActiivtyUtils.addFragmentToActivity(
                    getSupportFragmentManager(), taxCalculatorFragment, R.id.container);
        }


    }

}
