package sweden.hack.userinfo.network.response;

public class APIResponse<T> {

    final private int mStatusCode;
    final private T mContent;
    final private String mRawErrorBody;
    final private Throwable mThrowable;

    public APIResponse(T content, int statusCode) {
        this(content, statusCode, null, null);
    }

    public APIResponse(int statusCode, String rawErrorBody) {
        this(null, statusCode, rawErrorBody, null);
    }

    public APIResponse(Throwable throwable) {
        this(null, 0, null, throwable);
    }

    private APIResponse(T content, int statusCode, String rawErrorBody, Throwable throwable) {
        mContent = content;
        mStatusCode = statusCode;
        mRawErrorBody = rawErrorBody;
        mThrowable = throwable;
    }

    public boolean isSuccessful() {
        return mStatusCode >= 200 && mStatusCode < 300;
    }

    public T getContent() {
        return mContent;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public String getRawErrorBody() {
        return mRawErrorBody;
    }

}
