package com.trustedchoice.kontentdemo;

import kentico.kontent.delivery.DeliveryClient;
import kentico.kontent.delivery.DeliveryOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KontentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KontentDemoApplication.class, args);
    }

    @Bean
    public DeliveryClient deliveryClient() {
        final DeliveryOptions options = DeliveryOptions.builder()
                .projectId("dbbd47a5-ec53-00d3-bb37-a3d93b1d6e4a")
                .build();

        final DeliveryClient deliveryClient = new DeliveryClient(options);

        // Reusing DeliveryClient instance in ContentLinkUrlResolver causes timeout issue
        deliveryClient.setContentLinkUrlResolver(new SitemapLinkResolver(deliveryClient));

        // Creating new DeliveryClient instance in ContentLinkUrlResolver avoids timeout issue
//        deliveryClient.setContentLinkUrlResolver(new SitemapLinkResolver(new DeliveryClient(options)));

        return deliveryClient;
    }
}
