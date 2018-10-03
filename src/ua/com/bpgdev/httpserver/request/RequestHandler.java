package ua.com.bpgdev.httpserver.request;

import ua.com.bpgdev.httpserver.entity.Request;
import ua.com.bpgdev.httpserver.entity.Response;
import ua.com.bpgdev.httpserver.exception.*;
import ua.com.bpgdev.httpserver.resource.ResourceReader;
import ua.com.bpgdev.httpserver.responce.ResponseWriter;

import java.io.*;
import java.net.Socket;

public class RequestHandler {
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private String webAppPath;

    public RequestHandler(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void handle(Socket socket) throws IOException, HttpServerException {
        ResponseWriter resourceWriter = new ResponseWriter();
        try {
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


            Request request = (new RequestParser()).parseRequest(socketReader);
            ResourceReader resourceReader = new ResourceReader(webAppPath);
            Response responceContent = resourceReader.getResource(request.getURI());

            switch (request.getHttpMethod()) {
                case GET:
                    resourceWriter.writeSuccessResponse(socketWriter, responceContent.getResponseContent(),
                            responceContent.getResponseContentType());
                    break;
                default:
                    throw new InvalidHttpMethodException("Only HTTP GET method is implemented");
            }
        } catch (IllegalHttpHeaderException | InvalidHttpMethodException | InvalidUrlInRequestException e) {
            resourceWriter.writeBadRequestResponse(socketWriter, e.toString());
        } catch (ResourceNotFoundException e) {
            resourceWriter.writeNotFoundResponse(socketWriter, e.toString());
//        } catch (Exception e) {
//            resourceWriter.writeInternalServerErrorResponse(socketWriter, "");
        } finally {
            socketReader.close();
        }
    }
}
