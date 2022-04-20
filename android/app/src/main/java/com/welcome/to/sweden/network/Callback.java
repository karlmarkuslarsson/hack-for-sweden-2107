package com.welcome.to.sweden.network;

import androidx.annotation.NonNull;

import com.welcome.to.sweden.network.response.APIResponse;

public interface Callback<T> {

    void onSuccess(@NonNull APIResponse<T> response);

    void onFailure(@NonNull APIResponse<T> response);
}
