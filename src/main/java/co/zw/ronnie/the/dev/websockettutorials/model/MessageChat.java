package co.zw.ronnie.the.dev.websockettutorials.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageChat {
    private MessageType type;
    private String message;
    private String sender;
    private String time;
}
