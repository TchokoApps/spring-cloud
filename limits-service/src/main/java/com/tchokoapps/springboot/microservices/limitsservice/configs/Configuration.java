package com.tchokoapps.springboot.microservices.limitsservice.configs;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
@Data
@NoArgsConstructor
public class Configuration {

    private int minimum;
    private int maximum;

    public Configuration(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }
}
