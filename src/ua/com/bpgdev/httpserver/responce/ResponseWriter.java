package ua.com.bpgdev.httpserver.responce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ResponseWriter {
    private final static String HTTP_OK = "200 OK";
    private final static String HTTP_BAD_REQUEST = "400 Bad Request";
    private final static String HTTP_NOT_FOUND = "404 Not Found";
    private final static String HTTP_INTERNAL_SERVER_ERROR = "500 Internal Server Error";
    private final static String DEFAULT_RESPONSE_CONTENT_TYPE = "html";
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss ", Locale.US);


    public void writeSuccessResponse(BufferedWriter socketWriter, String responseContent, String responseContentType) throws IOException {
        socketWriter.write(getHttpHeader(HTTP_OK, LocalDateTime.now().format(dateTimeFormatter),
                responseContent.length(), responseContentType)
                + responseContent
                + "\n\n");
        socketWriter.flush();
    }

    public void writeBadRequestResponse(BufferedWriter socketWriter, String responseContent) throws IOException {
        socketWriter.write(getHttpHeader(HTTP_BAD_REQUEST, LocalDateTime.now().format(dateTimeFormatter),
                responseContent.length(), DEFAULT_RESPONSE_CONTENT_TYPE)
//                + responseContent
                + "\n\n");
        socketWriter.flush();
    }

    public void writeNotFoundResponse(BufferedWriter socketWriter, String responseContent) throws IOException {
        socketWriter.write(getHttpHeader(HTTP_NOT_FOUND, LocalDateTime.now().format(dateTimeFormatter),
                responseContent.length(), DEFAULT_RESPONSE_CONTENT_TYPE)
//                + responseContent
                + "\n\n");
        socketWriter.flush();
    }

    public void writeInternalServerErrorResponse(BufferedWriter socketWriter, String responseContent) throws IOException {
        socketWriter.write(getHttpHeader(HTTP_INTERNAL_SERVER_ERROR, LocalDateTime.now().format(dateTimeFormatter),
                responseContent.length(), DEFAULT_RESPONSE_CONTENT_TYPE)
//                + responseContent
                + "\n\n");
        socketWriter.flush();
    }

    private String getHttpHeader(String httpStatus, String responseTime, int responseLength, String responseContentType ) {
        String result;

        result = "HTTP/1.1 " + httpStatus + "\n" +
                "Date: " + responseTime + "\n" +
                "Server: BPGDev http server 0.1 (JVM)\n" +
                "Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT\n" +
                "Content-Length: " + responseLength + "\n" +
                "Content-Type: text/"+ responseContentType +"\n" +
                "Connection: Closed\n\n";

        return result;

    }
}
