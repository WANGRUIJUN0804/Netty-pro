package com.wrj.netty.client;

import com.alibaba.fastjson.JSONObject;
import com.wrj.netty.handler.SimpleClientHandler;
import com.wrj.netty.util.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @ClassName TcpClient
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-10 19:24
 * @Version 1.0
 */
public class TcpClient {
    static final Bootstrap b = new Bootstrap();
    static ChannelFuture f = null;
    static {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        b.group(workerGroup); // (2)
        b.channel(NioSocketChannel.class); // (3)
        b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));//只在收到消息的时候起作用
                ch.pipeline().addLast(new StringDecoder());//字节流解码成字符串，只在收到消息的时候有用
                ch.pipeline().addLast(new SimpleClientHandler());//依旧是处理收到的消息
                ch.pipeline().addLast(new StringEncoder());//将字符串编码成字节流
            }
        });
        String host = "localhost";
        int port = 8081;

        try {
           f = b.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    // 注意：让每一个请求都用同一个连接，并返回
    public static Response send(ClientRequest request) {
        f.channel().writeAndFlush(JSONObject.toJSONString(request));
        f.channel().writeAndFlush("\r\n");
        DefaultFuture df = new DefaultFuture(request);
        return df.get();
    }

}
