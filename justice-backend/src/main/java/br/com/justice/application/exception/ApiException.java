package br.com.justice.application.exception;

import br.com.justice.api.dto.ErrorDTO;
import br.com.justice.application.i18n.Translator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import static br.com.justice.application.i18n.Translator.toLocaleStatic;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ApiException extends RuntimeException {

  private List<ErrorDTO> erros;
  private HttpStatus status;

  public ApiException(List<ErrorDTO> erros, HttpStatus status) {
    super();
    this.erros = erros.stream().map(errorDTO -> ErrorDTO.builder()
        .message(toLocaleStatic(errorDTO.getMessage()))
        .exceptionMessage(errorDTO.getExceptionMessage())
        .build()).collect(Collectors.toList());
    this.status = status;
  }

  public ApiException(String message, HttpStatus status) {
    super();
    this.erros = Collections.singletonList(
        ErrorDTO.builder().message(toLocaleStatic(message)).build()
    );
    this.status = status;
  }
}
