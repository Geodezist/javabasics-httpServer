package ua.com.bpgdev.httpserver.exception;

public class InvalidHttpMethodException extends HttpServerException {
    public InvalidHttpMethodException() {
    }

    public InvalidHttpMethodException(String message) {
        super(message);
    }

    public InvalidHttpMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidHttpMethodException(Throwable cause) {
        super(cause);
    }

    public InvalidHttpMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
