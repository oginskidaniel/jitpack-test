/**
 * Copyright (c), Daniel Oginski,
 * Krakow, Poland
 *
 * All rights reserved. Dissemination, reproduction, or use of this material in source
 * and binary forms requires prior written permission from Daniel Oginski.
 */
package com.example.wsdemo;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

import java.io.IOException;

public class WsClient {

    private final WebSocketSession session;

    public WsClient(WebSocketSession session) {
        this.session = new ConcurrentWebSocketSessionDecorator(session, 2000, 32768);
    }

    public void send(String text) throws IOException {
        session.sendMessage(new TextMessage(text));
    }
}
