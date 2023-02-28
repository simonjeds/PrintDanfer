package br.com.araujo.danfeprinter.configs.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("microservices.sap.po")
public class SAPClientProperties {

  private String baseUrl;
  private String username;
  private String password;

}
