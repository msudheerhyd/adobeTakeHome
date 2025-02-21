package com.adobe.takehome.fictionsWebApp.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitingConfig {

    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.classic(5,
                Refill.intervally(5, Duration.ofSeconds(30)));
        return Bucket4j.builder()
                .addLimit(limit)
                        .build();
    }
}
