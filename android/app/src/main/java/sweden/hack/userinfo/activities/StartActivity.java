package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.di.DaggerUtils;
import sweden.hack.userinfo.dialogs.CurrencyDialog;
import sweden.hack.userinfo.dialogs.DatePickerDialog;
import sweden.hack.userinfo.dialogs.LengthDialog;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.models.currency.Currencies;
import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;

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
    HackOfSwedenApi mHackOfSwedenApi;

    private List<Currency> mCurrencyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerUtils.getComponent(this).inject(this);
        super.onCreate(savedInstanceState);

        if (mDataHelper.hasStarted()) {
            startMainActivity();
            return;
        }

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
        mHackOfSwedenApi.getCurrencies(new Callback<Currencies>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Currencies> response) {
                mCurrencyList = response.getContent().getValue();
            }

            @Override
            public void onFailure(@NonNull APIResponse<Currencies> response) {

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
                    public void onCurrencySelected(Currency currency) {
                        mCurrencyField.setText(currency.getName());
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
                LocalDate date = fmt.parseLocalDate(text);
                onValidDate(date);
            } catch (IllegalArgumentException e) {
                onInvalidDate(getString(R.string.input_invalid_date));
            }
        } else {
            onValidDate(null);
        }
    }

    private void onInvalidDate(String message) {
        mDateField.setError(message);
    }

    private void onValidDate(LocalDate localDate) {
        mDataHelper.setTripDate(localDate);
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
