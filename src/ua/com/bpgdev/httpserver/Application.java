package ua.com.bpgdev.httpserver;

import ua.com.bpgdev.httpserver.exception.HttpServerException;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, HttpServerException {
        String resourceRootPath = "resource\\webapp";
        int port = 3000;

        if (args.length > 0) {
            resourceRootPath = args[0];
            if (args.length > 1) {
                try {
                    port = Integer.parseInt(args[1]);
                    if (port < 1 || port > 65535) {
                        throw new IndexOutOfBoundsException();
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("To start HTTP server with custom resource folder & port please use: ");
                    System.out.println("    java.exe Application [path_to_resource_root] [port]");
                    System.out.println("Port value is valid from 1 to 65535");
                    System.exit(-1);
                }
            }
        }
        Server server = new Server();
        server.setPort(port);
        server.setWebAppPath(resourceRootPath);
        server.start();
    }
}
