package Servise;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class MessageProviderImpl implements MessageProvider{

    private Socket socket;
    private ObjectInputStream objectInputStream=null;
    private ObjectOutputStream objectOutputStream=null;

    public MessageProviderImpl(Socket socket) {
        this.socket = socket;
    }

    public MessageProviderImpl() {
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException,NullPointerException {
        this.socket = socket;
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectInputStream=new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageProviderImpl)) return false;
        MessageProviderImpl that = (MessageProviderImpl) o;
        return Objects.equals(socket, that.socket) &&
                Objects.equals(objectInputStream, that.objectInputStream) &&
                Objects.equals(objectOutputStream, that.objectOutputStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, objectInputStream, objectOutputStream);
    }

    @Override
    public String toString() {
        return "MessageProviderImpl{" +
                "socket=" + socket +
                ", objectInputStream=" + objectInputStream +
                ", objectOutputStream=" + objectOutputStream +
                '}';
    }

    @Override
    public void sendMessage(Message massage) throws IOException {
        try{
            objectOutputStream.writeObject(massage);
        }catch (IOException e){
            closeStream();
            throw e;
        }
    }

    @Override
    public Message readMessage() throws IOException {
        try{
            return (Message) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            closeStream();
            throw e;
        }
    }

    private final void closeStream(){
        try{
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
