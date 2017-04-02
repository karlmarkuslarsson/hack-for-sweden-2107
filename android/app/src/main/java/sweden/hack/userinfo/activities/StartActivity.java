package sweden.hack.userinfo.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.models.currency.Currencies;
import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;

public class StartActivity extends AppCompatActivity {

    private static final int MAX_NUMBER_OF_DAYS = 3;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Button mStartButton;
    private TextView mDateField;
    private TextView mLengthField;
    private TextView mCurrencyField;
    private List<Currency> mCurrencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (DataHelper.hasStarted()) {
            startLoadActivity();
            return;
        }

        getCurrencies();

        setContentView(R.layout.activity_start);
        initViews();
        setupViews();
        setStatusBarTranslucent();
        setupCallbacks();

    }

    private void getCurrencies() {
        HackOfSwedenApi.sharedInstance().getCurrencies(new Callback<Currencies>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Currencies> response) {
                mCurrencyList = response.getContent().getValue();
            }

            @Override
            public void onFailure(@NonNull APIResponse<Currencies> response) {

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
    }

    private void showCurrencyDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_currency);
        final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.dialog_currency_container);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < mCurrencyList.size(); i++) {
            RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.dialog_radio_button_row, radioGroup, false);
            radioGroup.addView(radioButton);
            if (mCurrencyList.get(i).getName().contains(mCurrencyField.getText().toString())) {
                radioButton.setChecked(true);
            }
            radioButton.setText(mCurrencyList.get(i).getCountry());
        }
        dialog.findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int index = radioGroup.indexOfChild(radioButton);
                setCurrency(mCurrencyList.get(index));
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final View upperDivider = dialog.findViewById(R.id.dialog_upper_divider);
        upperDivider.setVisibility(View.INVISIBLE);
        final View lowerDivider = dialog.findViewById(R.id.dialog_lower_divider);
        dialog.findViewById(R.id.dialog_scrollview)
                .setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (0 == scrollY) {
                            // top reached
                            upperDivider.setVisibility(View.INVISIBLE);
                        } else {
                            upperDivider.setVisibility(View.VISIBLE);

                        }

                        ScrollView scrollView = (ScrollView) v;
                        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
                        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                        if (diff == 0) {
                            // bottom reached
                            lowerDivider.setVisibility(View.INVISIBLE);
                        } else {
                            lowerDivider.setVisibility(View.VISIBLE);
                        }
                    }
                });
        dialog.show();
    }

    private void setCurrency(Currency currency) {
        mCurrencyField.setText(currency.getName());
    }

    private void showLengthDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_length);
        final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.dialog_length_container);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < MAX_NUMBER_OF_DAYS; i++) {
            RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.dialog_radio_button_row, radioGroup, false);
            radioGroup.addView(radioButton);

            if (i == 0) {
                radioButton.setText("1 day");
            } else {
                radioButton.setText(String.format("%d days", i + 1));
            }

            if (mLengthField.getText().toString().contains(String.valueOf(i + 1))) {
                radioButton.setChecked(true);
            }
        }

        dialog.findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int index = radioGroup.indexOfChild(radioButton);
                setDays(index + 1);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_date_picker);
        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);
        String selectedDate = mDateField.getText().toString();
        if (!selectedDate.isEmpty()) {
            setDatePicker(datePicker, selectedDate);
        }
        dialog.findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setDatePicker(DatePicker datePicker, String selectedDate) {
        DateTime date = DateTime.parse(selectedDate, DateTimeFormat.forPattern(DATE_FORMAT));
        datePicker.updateDate(date.getYear(), date.getMonthOfYear() - 1, date.getDayOfMonth());
    }

    private void setDate(int year, int month, int dayOfMonth) {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDate(year, month + 1, dayOfMonth);
        mDateField.setText(dateTime.toString(DATE_FORMAT));
    }

    private void setupCallbacks() {
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDateValid();
            }
        });
    }

    private void checkDateValid() {
        String text = mDateField.getText().toString();
        if (!text.isEmpty()) {
            try {
                DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
                String value = text.toString().trim();
                LocalDate date = fmt.parseLocalDate(value);
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
        DataHelper.setTripDate(localDate);
        DataHelper.setTripDays(Integer.parseInt(String.valueOf(mLengthField.getText().charAt(0))));
        DataHelper.hasStarted(true);
        startLoadActivity();
    }

    private void startLoadActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setStatusBarTranslucent() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private void initViews() {
        mStartButton = (Button) findViewById(R.id.activity_start_button);
        mDateField = (TextView) findViewById(R.id.activity_start_input);
        mLengthField = (TextView) findViewById(R.id.activity_start_length);
        mCurrencyField = (TextView) findViewById(R.id.activity_start_currency);
    }

}
