package com.bintou.mediscreen.note.exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String error;
    private String path;
    private int status;
    private Date timestamp;

    public ExceptionResponse (Date timestamp, int status, String error, String path) {
        timestamp = timestamp;
        status = status;
        error = error;
        path = path;
    }
}
