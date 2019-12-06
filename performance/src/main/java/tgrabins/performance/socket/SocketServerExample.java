package tgrabins.performance.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerExample {

    private static ServerSocket server;
    private static int port = 9999;

    public static void main(String[] args) throws IOException{
        server = new ServerSocket(port);

        System.out.println("Waiting for the client request");
        Socket socket = server.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        while(!socket.isClosed()){
            String message = ois.readUTF();
            System.out.println("Message from Client: " + message);
        }
    }

}
