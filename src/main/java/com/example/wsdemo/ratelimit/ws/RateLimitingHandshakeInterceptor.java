/**
 * Copyright (c), Daniel Oginski,
 * Krakow, Poland
 *
 * All rights reserved. Dissemination, reproduction, or use of this material in source
 * and binary forms requires prior written permission from Daniel Oginski.
 */
package com.example.wsdemo.ratelimit.ws;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;


public class RateLimitingHandshakeInterceptor implements HandshakeInterceptor {


    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler handler,
            Map<String, Object> attributes) throws Exception {


        System.out.println("jest ok");

        // Rate limit not exceeded, continue with the WebSocket session initialization
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}