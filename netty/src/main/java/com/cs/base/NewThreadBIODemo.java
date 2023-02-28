package com.cs.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewThreadBIODemo {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("0.0.0.0",8888),50);
        Socket socket;
        while ((socket = serverSocket.accept()) != null){
            final Socket clientSocket = socket;
            executorService.submit(() -> {
                try {
                    InputStream is = clientSocket.getInputStream();
                    byte[] data = new byte[1024];
                    is.read();

                    OutputStream out = clientSocket.getOutputStream();
                    out.write(data);
                    out.close();
                }catch (Exception e){
                    System.out.println("接受客户端请求失败:" + e.getCause());;
                }
            });
        }
    }
}
