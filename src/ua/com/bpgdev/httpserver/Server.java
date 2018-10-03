package ua.com.bpgdev.httpserver;

import ua.com.bpgdev.httpserver.exception.HttpServerException;
import ua.com.bpgdev.httpserver.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 class Server {
    private int port;
    private String webAppPath;

    void start() throws IOException, HttpServerException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true){
            Socket socket = serverSocket.accept();
            RequestHandler requestHandler = new RequestHandler(webAppPath);
            requestHandler.handle(socket);
            socket.close();
        }
    }

     void setPort(int port) {
        this.port = port;
    }

     void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }
}
