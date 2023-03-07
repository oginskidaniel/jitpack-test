/**
 * Copyright (c), Daniel Oginski,
 * Krakow, Poland
 *
 * All rights reserved. Dissemination, reproduction, or use of this material in source
 * and binary forms requires prior written permission from Daniel Oginski.
 */
package com.example.wsdemo;

import com.example.wsdemo.ratelimit.RateLimit;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/test")
public class WsController {

    private final WsClientsLookup lookup;

    public WsController(WsClientsLookup lookup) {
        this.lookup = lookup;
    }

    @PostMapping("/{id}")
    public void sendMessage(@PathVariable String id) throws IOException {
        lookup.get(id).send("hello");
    }

    @RateLimit
    @PostMapping("/rate")
    public void post() {
        System.out.println("testing rate limit");
    }

    @GetMapping
    public String get() {
        return "hello world";
    }

    @GetMapping("/sync/{id}")
    public void test(@PathVariable String id) {
        WsClient wsClient = lookup.get(id);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    wsClient.send("hello %s".formatted(finalI));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
