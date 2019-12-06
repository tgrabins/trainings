package tgrabins.performance.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientExample {

    public static void main(String[] args) throws  IOException, InterruptedException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        for(int i=0; i<1000;i++){
            oos.writeUTF("Hi Server " + i );
            Thread.sleep(100);
        }
        oos.close();
        socket.close();
    }
}
