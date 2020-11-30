package com.example.luban.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer extends Thread {
    @Override
    public void run() {
        try(Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            ){
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),8080));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {

                // 等待某信道就绪
                int selectInt = selector.select();

                if (selectInt == 0)
                    continue;
                // 取得迭代器.selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 有客户端连接请求时
                    if (selectionKey.isAcceptable())
                        handleAccept(selectionKey);
                    // 从客户端读取数据
                    if (selectionKey.isReadable())
                        handleRead(selectionKey);
                    // 向客户端发送数据
                    if (selectionKey.isWritable())
                        handleWrite(selectionKey);
                    iterator.remove();
                }
            }
            }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void handleAccept(SelectionKey selectionKey) throws Exception
    {
        // 返回创建此键的通道，接受客户端建立连接的请求，并返回 SocketChannel 对象
        ServerSocketChannel ServerSocketChannel = (ServerSocketChannel)selectionKey.channel();
        SocketChannel clientChannel = ServerSocketChannel.accept();
        // 非阻塞式
        clientChannel.configureBlocking(false);
        // 注册到selector
        clientChannel.register(selectionKey.selector(), SelectionKey.OP_READ);

        sendInfo(clientChannel, "连接服务器成功!");
        System.err.println("client IP :" + clientChannel.socket().getRemoteSocketAddress());
    }

    public static void handleRead(SelectionKey key) throws Exception
    {
        SocketChannel channel = (SocketChannel) key.channel();
        String msg = "";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(channel.read(buffer) > 0)
        {
            buffer.flip();
            while(buffer.hasRemaining())
                msg += new String(buffer.get(new byte[buffer.limit()]).array());
            buffer.clear();
        }

        System.err.println("收到客户端消息:"+msg);

        sendInfo(channel, "服务端消息test!");
    }


    public static void handleWrite(SelectionKey key)
    {
        System.out.println("服务端发送信息!");
    }

    public static void sendInfo(SocketChannel clientChannel, String msg) throws Exception {
        // 向客户端发送连接成功信息
        clientChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

        public static void main(String[] args) {
        NIOServer nioServer = new NIOServer();
        nioServer.start();

    }


    private void sayHelloWorld(ServerSocketChannel server) {
        try(SocketChannel client = server.accept();){
            client.write(Charset.defaultCharset().encode("hello world"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
