package com.welcome.to.sweden.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.welcome.to.sweden.R;

import static com.welcome.to.sweden.activities.StartActivity.DATE_FORMAT;

import androidx.annotation.NonNull;

public class DatePickerDialog extends Dialog {

    @BindView(R.id.date_picker)
    DatePicker mDatePicker;

    @BindView(R.id.dialog_ok)
    Button mOkButton;

    @BindView(R.id.dialog_cancel)
    Button mCancelButton;

    private final String mCurrentDate;
    private final DatePickerDialogListener mListener;

    public DatePickerDialog(
            @NonNull Context context,
            String currentDate,
            DatePickerDialogListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_date_picker);
        ButterKnife.bind(this);

        mCurrentDate = currentDate;
        mListener = listener;

        initViews();
        setupViews();
        setupCallbacks();
    }

    private void setupViews() {
        mDatePicker.setMinDate(System.currentTimeMillis());
    }

    private void initViews() {
        if (!mCurrentDate.isEmpty()) {
            DateTime date = DateTime.parse(mCurrentDate, DateTimeFormat.forPattern(DATE_FORMAT));
            mDatePicker.updateDate(date.getYear(), date.getMonthOfYear() - 1, date.getDayOfMonth());
        }
    }

    private void setupCallbacks() {
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
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

    private void setDate(int year, int month, int dayOfMonth) {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDate(year, month + 1, dayOfMonth);
        mListener.onDateSelected(dateTime.toString(DATE_FORMAT));
    }

    public interface DatePickerDialogListener {
        void onDateSelected(String dateString);
    }

}
