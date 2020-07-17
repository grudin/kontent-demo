package com.trustedchoice.kontentdemo;

import kentico.kontent.delivery.DeliveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class TestController {

    private final DeliveryClient deliveryClient;

    public TestController(DeliveryClient deliveryClient) {
        this.deliveryClient = deliveryClient;
    }

    @GetMapping("/test")
    public String test() throws ExecutionException, InterruptedException {
        return deliveryClient.getItem("article_a").toCompletableFuture().get().getItem().toString();
    }
}
