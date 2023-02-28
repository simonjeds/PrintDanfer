package br.com.araujo.danfeprinter.controllers;

import br.com.araujo.danfeprinter.services.DanfePrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/print")
@RequiredArgsConstructor
public class DanfePrinterController {

  private final DanfePrinterService service;

  @GetMapping("by-nfe-key/{nfeKey}")
  public ResponseEntity<byte[]> printDanfeByNfeKey(@PathVariable String nfeKey) {
    byte[] danfeContent = this.service.getPdfContentByNfeKey(nfeKey);
    return ResponseEntity
      .status(HttpStatus.OK)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
      .body(danfeContent);
  }

}
