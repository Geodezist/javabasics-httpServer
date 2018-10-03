package ua.com.bpgdev.httpserver.request;

import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.httpserver.entity.HttpMethod;
import ua.com.bpgdev.httpserver.entity.Request;
import ua.com.bpgdev.httpserver.exception.HttpServerException;
import ua.com.bpgdev.httpserver.exception.IllegalHttpHeaderException;
import ua.com.bpgdev.httpserver.exception.InvalidHttpMethodException;
import ua.com.bpgdev.httpserver.exception.InvalidUrlInRequestException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser requestParser;
    private Request request;
    private String requestFirstLine;
    private String requestHeaders;
    private BufferedReader requestFullTextBufferedReader;

    @Before
    public void setUp() {
        requestParser = new RequestParser();
        request = new Request();

        requestFirstLine = "GET /wiki/HTTP HTTP/1.1\n";
        requestHeaders = "Host: uk.wikipedia.org\n" +
                "User-Agent: firefox/5.0 (Linux; Debian 5.0.8; en-US; rv:1.8.1.7) Gecko/20070914 Firefox/2.0.0.7\n" +
                "Connection: close\n\n";
        String requestFullText = requestFirstLine + requestHeaders;

        requestFullTextBufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(requestFullText.getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    public void testParseRequest() throws IOException, HttpServerException {
        request = requestParser.parseRequest(requestFullTextBufferedReader);

        assertEquals(HttpMethod.GET, request.getHttpMethod());
        assertEquals("/wiki/HTTP", request.getURI());
        assertEquals(3, request.getHeaders().size());
    }

    @Test
    public void testInjectUrlAndHttpMethod() throws HttpServerException{
        requestParser.injectUrlAndHttpMethod(request, requestFirstLine);

        assertEquals(HttpMethod.GET, request.getHttpMethod());
        assertEquals("/wiki/HTTP", request.getURI());
    }

    @Test
    public void testInjectHeaders() throws IOException, IllegalHttpHeaderException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(requestHeaders.getBytes(StandardCharsets.UTF_8))));
        requestParser.injectHeaders(request, bufferedReader);

        assertEquals(3, request.getHeaders().size());
        assertEquals("uk.wikipedia.org", request.getHeaderValueByKey("Host"));
        assertEquals("close", request.getHeaderValueByKey("Connection"));
    }

    @Test(expected = InvalidHttpMethodException.class)
    public void testIllegalHttpMethod() throws HttpServerException {
        String badRequestLineIllegalHttpMethod = "Here is not any HTTP method!";
        requestParser.injectUrlAndHttpMethod(request, badRequestLineIllegalHttpMethod);
    }

    @Test (expected = InvalidUrlInRequestException.class)
    public void testIllegalURL() throws HttpServerException {
        String  badRequestLinePartialLine = "GETand.space.was.lost.com!";
        requestParser.injectUrlAndHttpMethod(request, badRequestLinePartialLine);
    }

    @Test (expected = IllegalHttpHeaderException.class)
    public void testIllegalHeaders() throws IOException, IllegalHttpHeaderException {
        BufferedReader badBufferedReader = new BufferedReader(new InputStreamReader(
          new ByteArrayInputStream(requestHeaders.replace(":","-").getBytes(StandardCharsets.UTF_8))));

        requestParser.injectHeaders(request, badBufferedReader);
    }
}