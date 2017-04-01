package sweden.hack.userinfo.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
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
    private Spinner mSpinner;

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(1, false);
        mDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
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
        DataHelper.setTripDays(mSpinner.getSelectedItemPosition() + 1);
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
        mSpinner = (Spinner) findViewById(R.id.days_spinner);
    }

}
