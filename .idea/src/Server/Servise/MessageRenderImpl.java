package Servise;

import java.text.SimpleDateFormat;

public class MessageRenderImpl implements MessageRander {
    @Override
    public String massegeRander(Message message) {
        if (message==null){
            throw new RuntimeException("Get null message");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy - hh:mm");
        String text=message.getSender()+" "+simpleDateFormat.format(message.getDepartmentTime())+">"+message.getText()
                +System.lineSeparator();
        return text;
    }
}
