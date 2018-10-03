package ua.com.bpgdev.httpserver.responce;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.Assert.*;

public class ResponseWriterTest {

    @Before
    public void setUp() {

    }

    @Test
    public void writeSuccessResponse() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.err));

    ResponseWriter responseWriter = new ResponseWriter();
    responseWriter.writeSuccessResponse( bufferedWriter, "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Hello world</title>\n" +
            "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "    Hi there!\n" +
            "</h1>\n" +
            "</body>\n" +
            "</html>", "html");
    }

    @Test
    public void writeBadRequestResponse() {
    }

    @Test
    public void writeNotFoundResponse() {
    }

    @Test
    public void writeInternalServerErrorResponse() {
    }
}