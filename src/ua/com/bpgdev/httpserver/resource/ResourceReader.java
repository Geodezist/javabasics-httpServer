package ua.com.bpgdev.httpserver.resource;

import ua.com.bpgdev.httpserver.entity.Response;
import ua.com.bpgdev.httpserver.exception.ResourceNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class ResourceReader {
    private String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public Response getResource(String url) throws ResourceNotFoundException {
        Response response = new Response();
        String lineString;
        StringJoiner resultResource = new StringJoiner("\n");
        Path path = Paths.get(".", webAppPath, url).normalize();

        try (BufferedReader fileReader = new BufferedReader(
                new FileReader(path.toAbsolutePath().toString()))) {

            while ((lineString = fileReader.readLine()) != null) {
                resultResource.add(lineString);
            }
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }

        String fileName = path.getFileName().toString();
        String responseContentType = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
        response.setResponseContent(resultResource.toString());
        response.setResponseContentType(responseContentType);
        return response;
    }
}
