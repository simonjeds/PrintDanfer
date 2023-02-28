package br.com.araujo.danfeprinter.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class ProblemDetails {

  private int status;
  @Builder.Default
  private String type = "error";
  private String title;
  private String message;
  private String detail;
  private List<Error> errors;

  public ProblemDetails setStatus(HttpStatus status) {
    this.status = status.value();
    return this;
  }

  public OffsetDateTime getTimestamp() {
    return OffsetDateTime.now();
  }

  public ProblemDetails addError(Error error) {
    if (Objects.isNull(this.errors)) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(error);
    return this;
  }

  @Getter
  @Setter
  @Builder
  public static class Error {
    private String name;
    private String message;
  }

}