package br.com.araujo.danfeprinter.services;

public interface DanfePrinterService {

  /**
   * Receives a nfe key of 44 digits and returns the danfe pdf content
   * @param nfeKey nfe key of 44 digits
   * @return byte array with DANFE PDF content
   */
  byte[] getPdfContentByNfeKey(String nfeKey);

}
