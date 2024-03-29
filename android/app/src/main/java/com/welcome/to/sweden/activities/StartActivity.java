package com.welcome.to.sweden.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.dialogs.CurrencyDialog;
import com.welcome.to.sweden.dialogs.DatePickerDialog;
import com.welcome.to.sweden.dialogs.LengthDialog;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.network.BasicCallback;
import com.welcome.to.sweden.network.HackOfSwedenLocalFilesApi;
import com.welcome.to.sweden.network.response.APIResponse;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    private static final int MAX_NUMBER_OF_DAYS = 3;
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @BindView(R.id.activity_start_button)
    Button mStartButton;

    @BindView(R.id.activity_start_input)
    TextView mDateField;

    @BindView(R.id.activity_start_length)
    TextView mLengthField;

    @BindView(R.id.activity_start_currency)
    TextView mCurrencyField;

    @Inject
    DataHelper mDataHelper;

    @Inject
    HackOfSwedenLocalFilesApi mHackOfSwedenLocalFilesApi;

    @Inject
    FirebaseAnalytics mFirebaseAnalytics;

    private List<String> mCurrencyList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerUtils.getComponent(this).inject(this);
        super.onCreate(savedInstanceState);

        if (mDataHelper.hasStarted()) {
            startMainActivity();
            return;
        }
        mFirebaseAnalytics.setCurrentScreen(this, MainActivity.class.getSimpleName(), null);

        loadInitialData();
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        setupViews();
        setupCallbacks();
    }

    private void loadInitialData() {
        getCurrencies();
    }

    private void setupCallbacks() {
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDateValid();
            }
        });
    }

    private void setupViews() {
        mLengthField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLengthDialog();
            }
        });
        mDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        mCurrencyField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCurrencyDialog();
            }
        });
        setStatusBarTranslucent();
    }

    private void getCurrencies() {
        mHackOfSwedenLocalFilesApi.getExchangeRates(new BasicCallback<ExchangeRates>() {
            @Override
            public void onSuccess(@NonNull APIResponse<ExchangeRates> response) {
                mCurrencyList.clear();
                mCurrencyList.addAll(response.getContent().rates.keySet());
                Collections.sort(mCurrencyList);
            }
        });
    }

    private void showCurrencyDialog() {
        final CurrencyDialog dialog = new CurrencyDialog(
                this,
                mCurrencyList,
                mCurrencyField.getText().toString(),
                new CurrencyDialog.CurrencyDialogListener() {
                    @Override
                    public void onCurrencySelected(String currency) {
                        mCurrencyField.setText(currency);
                    }
                });
        dialog.show();
    }

    private void showLengthDialog() {
        final LengthDialog dialog = new LengthDialog(
                this,
                MAX_NUMBER_OF_DAYS,
                mLengthField.getText().toString(),
                new LengthDialog.LengthDialogListener() {
                    @Override
                    public void onLengthSelected(int length) {
                        setDays(length);
                    }
                });
        dialog.show();
    }

    private void setDays(int days) {
        if (days == 1) {
            mLengthField.setText("1 day");
        } else {
            mLengthField.setText(String.format("%d days", days));
        }
    }

    private void showDatePickerDialog() {
        final DatePickerDialog dialog = new DatePickerDialog(
                this,
                mDateField.getText().toString(),
                new DatePickerDialog.DatePickerDialogListener() {
                    @Override
                    public void onDateSelected(String dateString) {
                        mDateField.setText(dateString);
                    }
                });
        dialog.show();
    }

    private void checkDateValid() {
        String text = mDateField.getText().toString().trim();
        if (!text.isEmpty()) {
            try {
                DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
                DateTime date = fmt.parseDateTime(text);
                onValidDate(date);
            } catch (IllegalArgumentException e) {
                onInvalidDate(getString(R.string.input_invalid_date_use_yyyy_mm_dd));
            }
        } else {
            onValidDate(null);
        }
    }

    private void onInvalidDate(String message) {
        mDateField.setError(message);
    }

    private void onValidDate(DateTime localDate) {
        mDataHelper.setTripDate(localDate != null ? localDate : new DateTime());
        mDataHelper.setTripDays(Integer.parseInt(String.valueOf(mLengthField.getText().charAt(0))));
        mDataHelper.setCurrency(mCurrencyField.getText().toString());
        mDataHelper.hasStarted(true);

        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setStatusBarTranslucent() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

}
