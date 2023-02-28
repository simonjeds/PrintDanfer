package br.com.araujo.danfeprinter.utils;

import br.com.araujo.danfeprinter.dtos.PrintDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class JasperReportUtils {

  private static PrintDTO getNFEPrintDataByXML(String xml) {
    PrintDTO nfePrintDTO = new PrintDTO();
    nfePrintDTO.setXml(xml);
    nfePrintDTO.setPathExpression("/nfeProc/NFe/infNFe/det");
    nfePrintDTO.setJasper(FileUtils.getFileAsStream("/jasper/nfe/danfe.jasper"));
    nfePrintDTO.addParameter("Logo", FileUtils.getFileAsStream("/img/logo.png"));
    nfePrintDTO.addParameter("SUBREPORT", FileUtils.getFileAsStream("/jasper/nfe/danfe_fatura.jasper"));
    nfePrintDTO.addParameter(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
    return nfePrintDTO;
  }

  public static JasperPrint generateJasperPrintByXML(
    String xml
  ) throws IOException, SAXException, ParserConfigurationException, JRException {
    PrintDTO print = getNFEPrintDataByXML(xml);
    DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    Document document = docBuilder.parse(new InputSource(new StringReader(print.getXml())));
    JRDataSource xmlDataSource = new JRXmlDataSource(document, print.getPathExpression());
    return JasperFillManager.fillReport(print.getJasper(), print.getParameters(), xmlDataSource);
  }

}
