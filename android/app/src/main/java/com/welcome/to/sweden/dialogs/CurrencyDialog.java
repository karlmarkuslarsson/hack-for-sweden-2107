package com.welcome.to.sweden.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.welcome.to.sweden.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyDialog extends Dialog {

    @BindView(R.id.dialog_currency_container)
    RadioGroup mRadioGroup;

    @BindView(R.id.dialog_ok)
    Button mOkButton;

    @BindView(R.id.dialog_cancel)
    Button mCancelButton;

    @BindView(R.id.dialog_upper_divider)
    View mUpperDivider;

    @BindView(R.id.dialog_lower_divider)
    View mLowerDivider;

    @BindView(R.id.dialog_scrollview)
    ScrollView mScrollView;

    private final List<String> mCurrencyList;
    private final String mCurrentCurrency;
    private CurrencyDialogListener mListener;

    public CurrencyDialog(
            @NonNull Context context,
            List<String> currencyList,
            String currentCurrency,
            CurrencyDialogListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_currency);
        ButterKnife.bind(this);
        mCurrencyList = currencyList;
        mCurrentCurrency = currentCurrency;
        mListener = listener;

        initViews();
        setupCallbacks();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < mCurrencyList.size(); i++) {
            RadioButton radioButton = (RadioButton) inflater
                    .inflate(R.layout.dialog_radio_button_row, mRadioGroup, false);
            mRadioGroup.addView(radioButton);
            if (mCurrencyList.get(i).contains(mCurrentCurrency)) {
                radioButton.setChecked(true);
            }
            radioButton.setText(mCurrencyList.get(i));
        }
    }

    private void setupCallbacks() {

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
                View radioButton = mRadioGroup.findViewById(radioButtonID);
                int index = mRadioGroup.indexOfChild(radioButton);
                mListener.onCurrencySelected(mCurrencyList.get(index));
                dismiss();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        mUpperDivider.setVisibility(View.INVISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (0 == scrollY) {
                        // top reached
                        mUpperDivider.setVisibility(View.INVISIBLE);
                    } else {
                        mUpperDivider.setVisibility(View.VISIBLE);

                    }

                    ScrollView scrollView = (ScrollView) v;
                    View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
                    int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                    if (diff == 0) {
                        // bottom reached
                        mLowerDivider.setVisibility(View.INVISIBLE);
                    } else {
                        mLowerDivider.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public interface CurrencyDialogListener {
        void onCurrencySelected(String currency);
    }

}
