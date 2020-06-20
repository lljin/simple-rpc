package com.lljin.scoket;

import java.io.*;
import java.net.Socket;

/**
 * @author lljin
 * @description 远程调用客户端
 * @date 2020/6/20 11:33
 */
public class RpcBIOClient implements RpcClient{
    private Socket socket ;
    private final String host;
    private final int port;

    public RpcBIOClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }

    private void init() {
        try {
            this.socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] remoteCall(byte[] message){
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(message);
            bufferedOutputStream.flush();
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buff = new byte[1024];
            int count = 0;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((count = bufferedInputStream.read(buff)) != -1){
                byteArrayOutputStream.write(buff,0,count);
                if (count != 1024){
                    break;
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("远程调用异常!");
    }

}
