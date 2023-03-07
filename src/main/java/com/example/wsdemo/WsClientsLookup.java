/**
 * Copyright (c), Daniel Oginski,
 * Krakow, Poland
 *
 * All rights reserved. Dissemination, reproduction, or use of this material in source
 * and binary forms requires prior written permission from Daniel Oginski.
 */
package com.example.wsdemo;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WsClientsLookup {

    private final Map<String, WsClient> lookup = new ConcurrentHashMap<>();

    public void store(String id, WsClient client) {
        System.out.println("WsClient id = " + id);
        lookup.put(id, client);
    }

    public WsClient get(String id) {
        return lookup.get(id);
    }
}
