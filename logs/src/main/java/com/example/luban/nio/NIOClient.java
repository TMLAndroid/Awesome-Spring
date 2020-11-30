package com.example.luban.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        try {
            Selector selector = Selector.open();
            SocketChannel client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
            client.register(selector, SelectionKey.OP_CONNECT);
            while (true) {
                int selectInt = selector.select();
                if (selectInt == 0)
                    continue;
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isConnectable())
                        handleConnect(key);
                    if (key.isReadable())
                        handleRead(key);
                    if (key.isWritable())
                        handleWrite(key);
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleWrite(SelectionKey key) throws Exception {
        System.err.println("客户端写数据!");
    }

    public static void handleConnect(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending())
            channel.finishConnect();
        channel.configureBlocking(false);
        channel.register(key.selector(), SelectionKey.OP_READ);

        sendInfo(channel, "客户端测试!");
    }

    public static void sendInfo(SocketChannel clientChannel, String msg) throws Exception {
        // 向客户端发送连接成功信息
        clientChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

    public static void handleRead(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        String msg = "";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) > 0) {
            buffer.flip();
            while (buffer.hasRemaining())
                msg += new String(buffer.get(new byte[buffer.limit()]).array());
            buffer.clear();
        }

        System.err.println("收到服务端消息:" + msg);

    }
}
