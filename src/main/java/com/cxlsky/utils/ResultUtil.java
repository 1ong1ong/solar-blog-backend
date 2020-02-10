package com.cxlsky.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * @author cxl
 */
@Data
public class ResultUtil implements Serializable {

    private static final long serialVersionUID = -3241012556895285106L;

    public static <T> ResponseEntity<T> ok(T data) {
        return ResponseEntity.ok(data);
    }

    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<T> badRequest(T data) {
        return ResponseEntity.badRequest().body(data);
    }

    public static <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    public static <T> ResponseEntity<T> unAuthorized(T data) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(data);
    }

    public static <T> ResponseEntity<T> unAuthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public static <T> ResponseEntity<T> forbidden(T data) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(data);
    }

    public static <T> ResponseEntity<T> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    public static <T> ResponseEntity<T> internalError(T data) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
    }

    public static <T> ResponseEntity<T> internalError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}