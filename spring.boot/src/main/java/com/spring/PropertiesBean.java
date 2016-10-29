package com.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2016/9/2.
 */
@Configuration
@ConfigurationProperties(prefix = "my")
public class PropertiesBean {

    private String host;
    private int port;

    private List<String> servers = new ArrayList<String>();

    private Map<String, String> dev = new HashMap<String, String>();

    private Map<String, String> prod = new HashMap<String, String>();





    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    public Map<String, String> getDev() {
        return dev;
    }

    public void setDev(Map<String, String> dev) {
        this.dev = dev;
    }

    public Map<String, String> getProd() {
        return prod;
    }

    public void setProd(Map<String, String> prod) {
        this.prod = prod;
    }
}
