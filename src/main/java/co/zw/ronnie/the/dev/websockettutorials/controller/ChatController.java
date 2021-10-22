package co.zw.ronnie.the.dev.websockettutorials.controller;

import co.zw.ronnie.the.dev.websockettutorials.model.MessageChat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public MessageChat sendMessage(@Payload final MessageChat messageChat){
        return messageChat;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public MessageChat newUser(@Payload final MessageChat messageChat, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessageHeaderAccessor.getSessionAttributes().put("username", messageChat.getSender());
        return  messageChat;
    }


}
