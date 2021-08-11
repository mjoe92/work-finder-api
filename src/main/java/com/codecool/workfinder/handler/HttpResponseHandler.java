package com.codecool.workfinder.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.*;

@Component
public class HttpResponseHandler {

    public ResponseEntity<?> getEntityWithStatus(Object object) {
        HttpStatus status = returnStatus_GET(object);
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(new HttpHeaders())
                .body(object);
    }

    private HttpStatus returnStatus_GET(Object object) {

        if (object == null) {
            return NOT_FOUND;
        } else if (object instanceof Integer) {

            int size = (Integer) object;
            if (size == 0) {
                return NOT_FOUND;
            } else if (size >= 100) {
                return PAYLOAD_TOO_LARGE;
            }
        }
        return OK;
    }
}
