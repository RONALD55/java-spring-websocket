package co.zw.ronnie.the.dev.websockettutorials.controller;


import co.zw.ronnie.the.dev.websockettutorials.model.MessageChat;
import co.zw.ronnie.the.dev.websockettutorials.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent sessionConnectedEvent){
            LOGGER.info("We have a new connection Mr Kanyepi Sir");
    }

    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent sessionDisconnectEvent){
        final StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        final String username = (String) stompHeaderAccessor.getSessionAttributes().get("username");
        final MessageChat messageChat = MessageChat.builder()
                                                    .type(MessageType.DISCONNECT)
                                                    .sender(username)
                                                    .build();
      simpMessageSendingOperations.convertAndSend("/topic/public",messageChat);

    }
}
