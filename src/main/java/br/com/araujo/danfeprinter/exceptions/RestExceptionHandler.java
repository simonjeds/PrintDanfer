package br.com.araujo.danfeprinter.exceptions;

import br.com.araujo.danfeprinter.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(RestClientException.class)
  public ResponseEntity<ProblemDetails> handleRestClientException(RestClientException ex) {
    log.error(ex.getMessage(), ex);
    return this.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getCause().getMessage());
  }

  @ExceptionHandler(GenericBusinessException.class)
  public ResponseEntity<ProblemDetails> handleBusinessException(GenericBusinessException ex) {
    log.error(ex.getMessage());
    return this.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetails> handleException(Exception ex) {
    log.error(ex.getMessage(), ex);
    return this.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Constants.GENERIC_ERROR);
  }

  private ResponseEntity<ProblemDetails> buildResponse(HttpStatus status, String error) {
    return this.buildResponse(status, error, error);
  }

  private ResponseEntity<ProblemDetails> buildResponse(HttpStatus status, String error, String detail) {
    ProblemDetails problem = ProblemDetails.builder()
      .status(status.value())
      .message(error)
      .detail(detail)
      .build();
    return ResponseEntity.status(status).body(problem);
  }

}
