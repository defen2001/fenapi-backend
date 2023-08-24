package com.defen.fenapiclientsdk;

import com.defen.fenapiclientsdk.client.FenApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("fenapi.client")
@Data
@ComponentScan
public class FenApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public FenApiClient deFenApiClient(){
        return new FenApiClient(accessKey,secretKey);
    }
}
