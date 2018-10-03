package ua.com.bpgdev.httpserver.exception;

public class InvalidUrlInRequestException extends HttpServerException {
    public InvalidUrlInRequestException() {
    }

    public InvalidUrlInRequestException(String message) {
        super(message);
    }

    public InvalidUrlInRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUrlInRequestException(Throwable cause) {
        super(cause);
    }

    public InvalidUrlInRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
