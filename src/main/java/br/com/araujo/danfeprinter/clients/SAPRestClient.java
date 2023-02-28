package br.com.araujo.danfeprinter.clients;

import br.com.araujo.danfeprinter.configs.properties.SAPClientProperties;
import br.com.araujo.danfeprinter.dtos.NfeXMLSapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SAPRestClient {

  private final RestTemplate restTemplate;
  private final SAPClientProperties properties;

  public String getXmlByNfeKey(String nfeKey) {
    String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
      .path("download-xml-nfe")
      .queryParam("nfeKey", nfeKey)
      .build().toUriString();

    HttpHeaders headers = this.getHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);
    HttpEntity<NfeXMLSapDTO> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, NfeXMLSapDTO.class);

    return Objects.requireNonNull(response.getBody()).getXmlStr();
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    headers.setBasicAuth(properties.getUsername(), properties.getPassword());
    return headers;
  }

}
