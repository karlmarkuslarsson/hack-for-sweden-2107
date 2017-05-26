package sweden.hack.userinfo.network.request;

public interface APIRequest<T> {

    APIRequest<T> execute();

    void cancel();

    boolean isCanceled();
}
