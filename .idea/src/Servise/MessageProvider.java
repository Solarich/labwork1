package Servise;

import java.io.IOException;

public interface MessageProvider {
    void sendMessage(Message massage) throws IOException;
    Message readMessage() throws  IOException;
}
