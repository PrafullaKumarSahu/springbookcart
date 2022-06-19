package com.serverwarrior.springbootcart.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
@Slf4j
@Order(1)
public class RequestResponseLogger implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CustomRequestWrapper CustomRequestWrapper = new CustomRequestWrapper( (HttpServletRequest) servletRequest );
        log.info("Request URI is: {}", CustomRequestWrapper.getRequestURI());
        log.info("Request Method is: {}", CustomRequestWrapper.getMethod());
        log.info("Request bODY is: {}", new String(CustomRequestWrapper.getByteArray()).replaceAll( "\n", " " ));
        filterChain.doFilter(CustomRequestWrapper, servletResponse);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
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
}
