package ua.com.bpgdev.httpserver.exception;

public class IllegalHttpHeaderException extends HttpServerException {
    public IllegalHttpHeaderException() {
    }

    public IllegalHttpHeaderException(String message) {
        super(message);
    }

    public IllegalHttpHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalHttpHeaderException(Throwable cause) {
        super(cause);
    }

    public IllegalHttpHeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
