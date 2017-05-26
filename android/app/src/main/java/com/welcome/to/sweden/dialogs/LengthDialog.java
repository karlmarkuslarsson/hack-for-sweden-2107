package com.welcome.to.sweden.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;

public class LengthDialog extends Dialog {

    @BindView(R.id.dialog_length_container)
    RadioGroup mRadioGroup;

    @BindView(R.id.dialog_ok)
    Button mOkButton;

    @BindView(R.id.dialog_cancel)
    Button mCancelButton;

    private final int mMaxNumberOfDays;
    private final String mCurrentLength;
    private final LengthDialogListener mListener;

    public LengthDialog(
            @NonNull Context context,
            int maxNumberOfDays,
            String currentLength,
            LengthDialogListener listener) {
        super(context);
        setContentView(R.layout.dialog_length);
        ButterKnife.bind(this);

        mMaxNumberOfDays = maxNumberOfDays;
        mCurrentLength = currentLength;
        mListener = listener;

        initViews();
        setupCallbacks();
    }

    private void setupCallbacks() {

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
                View radioButton = mRadioGroup.findViewById(radioButtonID);
                int index = mRadioGroup.indexOfChild(radioButton);
                mListener.onLengthSelected(index + 1);
                dismiss();
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < mMaxNumberOfDays; i++) {
            RadioButton radioButton = (RadioButton)
                    inflater.inflate(R.layout.dialog_radio_button_row, mRadioGroup, false);
            mRadioGroup.addView(radioButton);

            if (i == 0) {
                radioButton.setText("1 day");
            } else {
                radioButton.setText(String.format("%d days", i + 1));
            }

            if (mCurrentLength.contains(String.valueOf(i + 1))) {
                radioButton.setChecked(true);
            }
        }
    }

    public interface LengthDialogListener {
        void onLengthSelected(int length);
    }

}
