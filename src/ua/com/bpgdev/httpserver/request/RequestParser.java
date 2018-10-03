package ua.com.bpgdev.httpserver.request;

import ua.com.bpgdev.httpserver.entity.HttpMethod;
import ua.com.bpgdev.httpserver.entity.Request;
import ua.com.bpgdev.httpserver.exception.HttpServerException;
import ua.com.bpgdev.httpserver.exception.IllegalHttpHeaderException;
import ua.com.bpgdev.httpserver.exception.InvalidHttpMethodException;
import ua.com.bpgdev.httpserver.exception.InvalidUrlInRequestException;

import java.io.BufferedReader;
import java.io.IOException;

class RequestParser {

    Request parseRequest(BufferedReader socketReader) throws IOException, HttpServerException {
        Request requestResult = new Request();
        String requestLine = socketReader.readLine();
        injectUrlAndHttpMethod(requestResult, requestLine);
        injectHeaders(requestResult, socketReader);

        return requestResult;
    }

    // Package private for testing purpose only
    void injectUrlAndHttpMethod(Request request, String requestLine)
            throws InvalidHttpMethodException, InvalidUrlInRequestException {
        try {
            String[] splittedRequestLine = requestLine.split(" ");
            String httpMethod = splittedRequestLine[0];
            String URI = splittedRequestLine[1];
            request.setHttpMethod(HttpMethod.valueOf(httpMethod));
            request.setURI(URI);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidUrlInRequestException(e);
        } catch (IllegalArgumentException e) {
            throw new InvalidHttpMethodException(e);
        }
    }

    // Package private for testing purpose only
    void injectHeaders(Request request, BufferedReader socketReader)
            throws IOException, IllegalHttpHeaderException {
        String requestLine;
        try {
            while (!(requestLine = socketReader.readLine()).isEmpty()) {
                String headerKey = requestLine.substring(0, requestLine.indexOf(":")).trim().toLowerCase();
                String headerValue = requestLine.substring(requestLine.indexOf(":") + 1).trim();

                request.setHeaders(headerKey, headerValue);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalHttpHeaderException(e);
        }
    }

}
