package com.welcome.to.sweden.files;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.response.APIResponse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DefaultObserver;

public class JsonFileReader {

    private final Gson mGson;
    private final Context mContext;
    private final Scheduler mIOScheduler;
    private final Scheduler mMainScheduler;

    public JsonFileReader(Context mContext, Gson gson, Scheduler mIOScheduler, Scheduler mMainScheduler) {
        this.mContext = mContext;
        this.mGson = gson;
        this.mIOScheduler = mIOScheduler;
        this.mMainScheduler = mMainScheduler;
    }

    public  <T> void read(final String fileName, final Class<T> clz, final Callback<T> callback) {
        Observable.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                AssetManager assets = mContext.getAssets();
                InputStream inputStream = assets.open(fileName);
                InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
                return mGson.fromJson(streamReader, clz);
            }
        })
        .subscribeOn(mIOScheduler)
        .observeOn(mMainScheduler)
        .subscribe(new DefaultObserver<T>() {
            @Override
            public void onNext(T value) {
                callback.onSuccess(new APIResponse<>(value, 200));
            }

            @Override
            public void onError(Throwable e) {
                callback.onFailure(new APIResponse<T>(e));
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
