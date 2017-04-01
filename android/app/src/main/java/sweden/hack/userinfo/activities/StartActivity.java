package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.DataHelper;

public class StartActivity extends AppCompatActivity {

    private Button mStartButton;
    private EditText mDateField;
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
        Editable text = mDateField.getText();
        if (text.length() > 0) {
            try {
                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
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
        mDateField = (EditText) findViewById(R.id.activity_start_input);
        mSpinner = (Spinner) findViewById(R.id.days_spinner);
    }

}
