package com.serverwarrior.springbootcart.filters;

import org.springframework.util.Assert;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

public class CustomeDelegatingServletOutputStream extends ServletOutputStream {
    private final OutputStream targetStream;

    public CustomeDelegatingServletOutputStream(OutputStream targetStream) {
        Assert.notNull(targetStream, "Target OutputStream must not be null");
        this.targetStream = targetStream;
    }

    public final OutputStream getTargetStream() {
        return this.targetStream;
    }

    public void write(int b) throws IOException {
        this.targetStream.write(b);
    }

    public void flush() throws IOException {
        super.flush();
        this.targetStream.flush();
    }

    public void close() throws IOException {
        super.close();
        this.targetStream.close();
    }

    public boolean isReady() {
        return true;
    }

    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException();
    }
}
