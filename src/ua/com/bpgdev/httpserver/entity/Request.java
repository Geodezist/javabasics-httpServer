package ua.com.bpgdev.httpserver.entity;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String URI;
    private HttpMethod httpMethod;
    private Map<String, String> headers;

    public void setHeaders(String headerKey, String headerValue) {
        if(headers == null){
            headers = new HashMap<>();
        }
        headers.put(headerKey, headerValue);
    }

    public String getHeaderValueByKey(String key){
        return headers.get(key.toLowerCase());
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }



}
