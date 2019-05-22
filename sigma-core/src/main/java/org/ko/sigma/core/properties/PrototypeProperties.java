package org.ko.sigma.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "prototype")
public class PrototypeProperties {

    private SecurityProperties security = new SecurityProperties();

    private FileProperties file = new FileProperties();

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }

    public FileProperties getFile() {
        return file;
    }

    public void setFile(FileProperties file) {
        this.file = file;
    }
}
