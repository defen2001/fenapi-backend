package com.defen.fenapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fenapi.gateway")
@Data
public class GatewayConfig {

    private String host;

}
