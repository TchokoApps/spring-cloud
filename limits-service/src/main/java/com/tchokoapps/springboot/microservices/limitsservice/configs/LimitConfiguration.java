package com.tchokoapps.springboot.microservices.limitsservice.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class LimitConfiguration {

    private int minimum;
    private int maximum;

    public LimitConfiguration(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }
}
