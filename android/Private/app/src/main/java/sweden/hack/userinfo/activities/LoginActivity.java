package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        setContentView(R.layout.activity_login);
        initViews();
        setupCallbacks();
    }

    private void setupCallbacks() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mInputField.getText().toString();

                // TODO: condition for input field
                if (true) {
                    DataHelper.setUserPersonNumber(input);
                    startMainActivity();
                } else {

                }
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initViews() {
        mLoginButton = findViewById(R.id.activity_login_button);
        mInputField = (EditText) findViewById(R.id.activity_login_input);
    }

}
