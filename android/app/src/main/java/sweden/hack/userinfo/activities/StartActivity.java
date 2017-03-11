package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.R;

public class StartActivity extends AppCompatActivity {

    private Button mStartButton;
    private EditText mDateField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Cache.sharedInstance().hasStarted()) {
            startMainActivity();
            return;
        }

        setContentView(R.layout.activity_start);
        initViews();
        setStatusBarTranslucent();
        setupCallbacks();

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
            startMainActivity();
        }
    }

    private void onInvalidDate(String message) {
        mDateField.setError(message);
    }

    private void onValidDate(LocalDate localDate) {
        Cache.sharedInstance().setTripDate(localDate);
        Cache.sharedInstance().hasStarted(true);
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

    private void initViews() {
        mStartButton = (Button) findViewById(R.id.activity_start_button);
        mDateField = (EditText) findViewById(R.id.activity_start_input);
    }

}
