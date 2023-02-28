package br.com.araujo.danfeprinter.utils;

import org.apache.commons.io.IOUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

public class FileUtils {

  public static String getFileAsString(String path) {
    try {
      Resource resource = new ClassPathResource(path);
      return IOUtil.toString(resource.getInputStream(), Charset.defaultCharset().toString());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static InputStream getFileAsStream(String path) {
    try {
      return new ClassPathResource(path).getInputStream();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
