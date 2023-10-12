package com.example.model.api;

public class ResourceResponse {
    private String resource;
    private String message;

    public void setResource(String inputResource) {
        this.resource = inputResource;
    }
    public String getResource() {
        return this.resource;
    }

    public void setMessage(String inputMessage) {
        this.message = inputMessage;
    }
    public String getMessage() {
        return this.message;
    }
}
