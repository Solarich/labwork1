package Servise;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SingleConnection implements Runnable{
    private MessageProvider messageProvider;
    private List<SingleConnection> connectionList;
    private List<Message> messageList;
    private int connectionID;

    public SingleConnection(MessageProvider messageProvider, List<SingleConnection> connectionList,
                            List<Message> messageList, int connectionID) {
        this.messageProvider = messageProvider;
        this.connectionList = connectionList;
        this.messageList = messageList;
        this.connectionID = connectionID;
    }
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }

    private final void addToConnectionList() {
        this.connectionList.add(this);
    }

    private final void deleteFromConnectionList() {
        this.connectionList.remove(this);
    }

    @Override
    public void run() {
        addToConnectionList();
        Thread thread = Thread.currentThread();
        try{
            for (Message messageTemp:messageList) {
                this.messageProvider.sendMessage(messageTemp);
            }
            for (;!thread.isInterrupted();) {
                Message message = this.messageProvider.readMessage();
                if (message==null){
                    throw new IOException();
                }
                if (message !=null){
                    messageList.add(message);
                }
                for (SingleConnection singleConnection : connectionList) {
                    singleConnection.getMessageProvider().sendMessage(message);
                }
            }
        }catch (IOException e){
            System.out.print(e);
            deleteFromConnectionList();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleConnection)) return false;
        SingleConnection that = (SingleConnection) o;
        return connectionID == that.connectionID &&
                Objects.equals(messageProvider, that.messageProvider) &&
                Objects.equals(connectionList, that.connectionList) &&
                Objects.equals(messageList, that.messageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageProvider, connectionList, messageList, connectionID);
    }

    @Override
    public String toString() {
        return "SingleConnection{" +
                "messageProvider=" + messageProvider +
                ", connectionList=" + connectionList +
                ", messageList=" + messageList +
                ", connectionID=" + connectionID +
                '}';
    }
}
