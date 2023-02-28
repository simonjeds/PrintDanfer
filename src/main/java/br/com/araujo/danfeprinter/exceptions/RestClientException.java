package br.com.araujo.danfeprinter.exceptions;

public class RestClientException extends RuntimeException {

  public RestClientException(String message, Throwable cause) {
    super(message, cause);
  }

}