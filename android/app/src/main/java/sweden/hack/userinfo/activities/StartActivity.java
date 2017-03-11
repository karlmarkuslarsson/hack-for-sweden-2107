package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.DataHelper;

public class StartActivity extends AppCompatActivity {
    private Button mLoginButton;
    private EditText mPersonNumberField;
    private EditText mFirstNameField;
    private EditText mSurnameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!userSignedIn()) {
            setContentView(R.layout.activity_login);
            initViews();
            setupCallbacks();
        }
    }

    private boolean userSignedIn() {
        String personNumber = DataHelper.getUserPersonNumber();
        if (personNumber != null && personNumber.length() == 10) {
            startMainActivity();
            return true;
        }
        return false;
    }

    private void setupCallbacks() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personNumber = mPersonNumberField.getText().toString();
                String firstName = mFirstNameField.getText().toString();
                String surname = mSurnameField.getText().toString();

                // TODO: condition for input field
                if (checkIfInputIsCorrect(personNumber, firstName, surname)) {
                    DataHelper.setUserPersonNumber(personNumber);
                    DataHelper.setFirstName(firstName);
                    DataHelper.setSurname(surname);
                    startMainActivity();
                }
            }
        });

        mPersonNumberField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mLoginButton.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean checkIfInputIsCorrect(String personNumber, String firstName, String surname) {
        boolean success = true;
        if (personNumber.length() != 10) {
            mPersonNumberField.setError(getString(R.string.person_number_minimum_length_error));
            success = false;
        }

        if (firstName.isEmpty()) {
            mFirstNameField.setError(getString(R.string.name_length_error));
            success = false;
        }

        if (surname.isEmpty()) {
            mSurnameField.setError(getString(R.string.name_length_error));
            success = false;
        }

        // add more checks..

        return success;
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        mLoginButton = (Button) findViewById(R.id.activity_login_button);
        mPersonNumberField = (EditText) findViewById(R.id.activity_login_input);
        mFirstNameField = (EditText) findViewById(R.id.activity_login_first_name);
        mSurnameField = (EditText) findViewById(R.id.activity_login_surname);
    }

}
