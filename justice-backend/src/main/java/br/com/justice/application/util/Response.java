package br.com.justice.application.util;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Objects.nonNull;

public interface Response<T> {

  default ResponseEntity<T> buildResponse(T response) {
    return nonNull(response)
        ? ResponseEntity.ok(response)
        : ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(null);
  }

  default ResponseEntity<List<T>> buildResponse(List<T> response) {
    return nonNull(response)
        ? ResponseEntity.ok(response)
        : ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(null);
  }
}