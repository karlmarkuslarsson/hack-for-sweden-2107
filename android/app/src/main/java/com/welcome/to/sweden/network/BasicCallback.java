package com.welcome.to.sweden.network;

import android.support.annotation.NonNull;

import com.welcome.to.sweden.network.response.APIResponse;

import timber.log.Timber;

public abstract class BasicCallback<T> implements Callback<T> {

    @Override
    public abstract void onSuccess(@NonNull APIResponse<T> response);

    @Override
    public final void onFailure(@NonNull APIResponse<T> response) {
        Throwable t = response.getThrowable();
        String errorBody = response.getRawErrorBody();
        Timber.e(t, "Failed to get response, the error body was: %s", errorBody);
    }
}
