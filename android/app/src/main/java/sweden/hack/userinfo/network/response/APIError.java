package sweden.hack.userinfo.network.response;

public class APIError extends RuntimeException {

    public APIError(Throwable cause) {
        super(cause);
    }

    public APIError(String message) {
        super(message);
    }

}