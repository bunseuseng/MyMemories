package com.group5.MyMemories.exception;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
        throw new ResourceNotFoundException("Memory not found with id: " + message);
    }
}