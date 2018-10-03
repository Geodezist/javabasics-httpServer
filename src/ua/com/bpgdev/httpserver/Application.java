package ua.com.bpgdev.httpserver;

import ua.com.bpgdev.httpserver.exception.HttpServerException;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, HttpServerException {
        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("resource\\webapp");
        server.start();
    }
}
