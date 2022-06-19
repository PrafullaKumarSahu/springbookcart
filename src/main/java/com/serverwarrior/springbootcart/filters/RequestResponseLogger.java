package com.serverwarrior.springbootcart.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

@Component
@Slf4j
@Order(1)
public class RequestResponseLogger implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper( (HttpServletRequest) servletRequest );
        log.info("Request URI is: {}", customRequestWrapper.getRequestURI());
        log.info("Request Method is: {}", customRequestWrapper.getMethod());
        log.info("Request Body is: {}", new String(customRequestWrapper.getByteArray()).replaceAll( "\n", " " ));

        CustomResponseWrapper customResponseWrapper = new CustomResponseWrapper( (HttpServletResponse) servletResponse );
        filterChain.doFilter(customRequestWrapper, customResponseWrapper);
        log.info("Response Status is: {}", customResponseWrapper.getStatus());
        log.info("Response Body is: {}", new String(customResponseWrapper.getByteArrayOutputStream().toByteArray()).replaceAll( "\n", " " ));
    }

    private class CustomRequestWrapper extends HttpServletRequestWrapper {

        public byte[] getByteArray() {
            return byteArray;
        }

        private byte[] byteArray;
        public CustomRequestWrapper(HttpServletRequest request) {
            super(request);
            try {
                byteArray = IOUtils.toByteArray(request.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("Issue while reading request input stream");
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new CustomDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
        }
    }

    private class CustomResponseWrapper extends HttpServletResponseWrapper {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        public CustomResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new CustomeDelegatingServletOutputStream( new TeeOutputStream(super.getOutputStream(), printStream) );
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter( new TeeOutputStream(super.getOutputStream(), printStream) );
        }

        public ByteArrayOutputStream getByteArrayOutputStream() {
            return byteArrayOutputStream;
        }
    }
}
