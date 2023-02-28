package com.cs.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BIODemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("0.0.0.0",8888), 50);
        Socket socket;
        while ((socket = serverSocket.accept()) != null){
            InputStream is = socket.getInputStream();
            byte[] data = new byte[1024];
            is.read(data);

            System.out.println(new String(data,UTF_8));
            OutputStream out = socket.getOutputStream();
            out.write(data);
            socket.close();
        }
    }
}
