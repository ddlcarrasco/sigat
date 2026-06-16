package com.sigat.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
    private String timestamp;
    private String path;

    public ApiResponse(int status, String message, T data, String path) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.path = path;
        this.timestamp = LocalDateTime.now().toString();
    }

    public int getStatus()       { return status; }
    public String getMessage()   { return message; }
    public T getData()           { return data; }
    public String getTimestamp() { return timestamp; }
    public String getPath()      { return path; }
}
