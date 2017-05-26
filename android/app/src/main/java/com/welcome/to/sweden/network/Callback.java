package sweden.hack.userinfo.network;

import android.support.annotation.NonNull;

import sweden.hack.userinfo.network.response.APIResponse;

public interface Callback<T> {

    void onSuccess(@NonNull APIResponse<T> response);

    void onFailure(@NonNull APIResponse<T> response);
}
