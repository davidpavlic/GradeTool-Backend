package com.accenture.gradetool.domain.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mail")
public class MailProperties {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public MailProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MailProperties setPort(int port) {
        this.port = port;
        return this;
    }
}
