package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.DataHelper;

public class StartActivity extends AppCompatActivity {

    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!userSignedIn()) {
            setContentView(R.layout.activity_start);
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
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    private boolean checkIfInputIsCorrect(String personNumber, String firstName, String surname) {
        boolean success = true;

        // add more checks..

        return success;
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        mStartButton = (Button) findViewById(R.id.activity_start_button);
    }

}
