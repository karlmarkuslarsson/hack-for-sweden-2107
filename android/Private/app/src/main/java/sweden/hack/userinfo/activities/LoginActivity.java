package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.DataHelper;

/**
 * Created by sosv on 04/03/17.
 */

public class LoginActivity extends AppCompatActivity {
    private View mLoginButton;
    private EditText mInputField;

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
                String input = mInputField.getText().toString();

                // TODO: condition for input field
                if (checkIfInputIsCorrect(input)) {
                    DataHelper.setUserPersonNumber(input);
                    startMainActivity();
                }
            }
        });

        mInputField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
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

    private boolean checkIfInputIsCorrect(String input) {
        if (input.length() != 10) {
            mInputField.setError(getString(R.string.person_number_minimum_length_error));
            return false;
        }

        // add more checks..

        return true;
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        mLoginButton = findViewById(R.id.activity_login_button);
        mInputField = (EditText) findViewById(R.id.activity_login_input);
    }

}
