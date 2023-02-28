package br.com.araujo.danfeprinter.services.impl;

import br.com.araujo.danfeprinter.clients.SAPRestClient;
import br.com.araujo.danfeprinter.exceptions.RestClientException;
import br.com.araujo.danfeprinter.services.DanfePrinterService;
import br.com.araujo.danfeprinter.utils.JasperReportUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

@Log4j2
@Service
@RequiredArgsConstructor
public class DanfePrinterServiceImpl implements DanfePrinterService {

  private final SAPRestClient sapRestClient;

  @Override
  public byte[] getPdfContentByNfeKey(String nfeKey) {
    try {
      String xml = this.sapRestClient.getXmlByNfeKey(nfeKey);
      JasperPrint jasperPrint = JasperReportUtils.generateJasperPrintByXML(xml);
      return JasperExportManager.exportReportToPdf(jasperPrint);
    } catch (RestClientResponseException ex) {
      String message = String.format("Ocorreu um erro ao tentar buscar o XML da nota no SAP para a chave: %s", nfeKey);
      throw new RestClientException(message, ex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
