package com.welcome.to.sweden.network.request;

public interface APIRequest<T> {

    APIRequest<T> execute();

    void cancel();

    boolean isCanceled();
}
