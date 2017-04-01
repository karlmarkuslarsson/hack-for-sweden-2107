package sweden.hack.userinfo.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.DataHelper;

public class StartActivity extends AppCompatActivity {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Button mStartButton;
    private TextView mDateField;
    private TextView mLengthField;
    private TextView mCurrencyField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (DataHelper.hasStarted()) {
            startLoadActivity();
            return;
        }

        setContentView(R.layout.activity_start);
        initViews();
        setupViews();
        setStatusBarTranslucent();
        setupCallbacks();

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
        RadioGroup container = (RadioGroup) dialog.findViewById(R.id.dialog_currency_container);

        int sideMargin = (int) getResources().getDimension(R.dimen.dialog_default_margin);
        for (int i = 0; i < 3; i++) {
            RadioButton button = new RadioButton(this);
            container.addView(button);

            RadioGroup.LayoutParams params = (RadioGroup.LayoutParams) button.getLayoutParams();
            params.width = RadioGroup.LayoutParams.MATCH_PARENT;
            params.leftMargin = sideMargin;
            params.rightMargin = sideMargin;

            button.setText("Button " + i);
        }

        dialog.show();
    }

    private void showLengthDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_length);
        dialog.findViewById(R.id.dialog_one_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDays(1);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_two_days).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDays(2);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_three_days).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDays(3);
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
