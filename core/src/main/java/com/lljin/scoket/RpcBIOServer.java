package com.lljin.scoket;

import com.lljin.support.ClientMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/**
 * @author lljin
 * @description 通信server端
 * @date 2020/6/20 8:35
 */
public class RpcBIOServer implements RpcServer {
    ExecutorService executorService = Executors.newCachedThreadPool();
    LinkedList<Socket> sockets = new LinkedList<>();
    ServerSocket serverSocket;
    LinkedBlockingQueue<ClientMessage> queue = new LinkedBlockingQueue<>();
    int port;

    public RpcBIOServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("rpc server start on "+port);
        this.port = port;
        initServer();
    }

    private void initServer() {
        executorService.submit(() -> {
            try {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                handlerInput(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handlerInput(Socket socket) {
        executorService.submit(() -> {
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                while (true) {
                    int count = 0;
                    while ((count = bufferedInputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, count);
                        if (count > 0 && count != 1024) {
                            queue.put(new ClientMessage(socket, byteArrayOutputStream.toByteArray()));
                            byteArrayOutputStream.reset();
                            break;
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void onMessage(Consumer<ClientMessage> consumer) {
        executorService.execute(() -> {
            while (true) {
                try {
                    ClientMessage take = queue.take();
                    consumer.accept(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void replay(byte[] message, Object destination) throws IOException {
        Socket dest = (Socket) destination;
        OutputStream outputStream = dest.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(message);
        bufferedOutputStream.flush();
    }
}
