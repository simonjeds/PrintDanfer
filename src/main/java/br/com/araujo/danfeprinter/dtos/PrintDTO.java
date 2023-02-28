package br.com.araujo.danfeprinter.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PrintDTO {

  private String xml;
  private InputStream jasper;
  private String pathExpression;
  private final Map<String, Object> parameters = new HashMap<>();

  public void addParameter(String name, Object value) {
    this.parameters.put(name, value);
  }

}
