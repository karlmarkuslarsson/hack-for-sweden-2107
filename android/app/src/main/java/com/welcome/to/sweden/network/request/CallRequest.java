package com.welcome.to.sweden.network.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.response.APIError;
import com.welcome.to.sweden.network.response.APIResponse;

public class CallRequest<T>
        implements APIRequest<T> {

    @NonNull
    private Call<T> mCall;

    @Nullable
    private Callback<T> mCallback;

    public CallRequest(@NonNull Call<T> call, @Nullable Callback<T> callback) {
        mCall = call;
        mCallback = callback;
    }

    @Override
    public APIRequest<T> execute() {
        mCall.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (mCallback != null) {
                    try {
                        APIResponse<T> apiResponse;

                        if (response.isSuccessful()) {
                            apiResponse = new APIResponse<>(response.body(), response.code());
                            mCallback.onSuccess(apiResponse);
                        } else {
                            String rawErrorBody = response.errorBody().string();
                            response.errorBody().close();
                            apiResponse = new APIResponse<>(response.code(), rawErrorBody);
                            mCallback.onFailure(apiResponse);
                        }

                    } catch (IOException e) {
                        throw new APIError(e);
                    }
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {

                if (mCallback != null) {
                    mCallback.onFailure(new APIResponse<T>(throwable));
                }

            }
        });

        return this;
    }

    @Override
    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
            mCall = null;
        }
        mCallback = null;
    }

    @Override
    public boolean isCanceled() {
        return mCall == null || mCall.isCanceled();
    }

}