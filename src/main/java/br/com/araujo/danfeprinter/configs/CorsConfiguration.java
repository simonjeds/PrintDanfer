package br.com.araujo.danfeprinter.configs;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CorsConfiguration extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
    HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HttpHeaders.ORIGIN));
    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
    response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Content-Type, Accept");

    if (HttpMethod.OPTIONS.matches(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
      return;
    }

    filterChain.doFilter(request, response);
  }

}