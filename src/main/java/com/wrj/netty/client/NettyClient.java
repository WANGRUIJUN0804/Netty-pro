package com.wrj.netty.client;

import com.wrj.netty.handler.SimpleClientHandler;
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
import io.netty.util.AttributeKey;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-09 17:09
 * @Version 1.0
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 8081;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup) // (2)
                    .channel(NioSocketChannel.class) // (3)
                    .option(ChannelOption.SO_KEEPALIVE, true) // (4)
                    .handler(new ChannelInitializer<SocketChannel>() { // (5)它添加的处理器是附加到客户端用于与服务器通信的 NioSocketChannel
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new SimpleClientHandler());
                            ch.pipeline().addLast(new StringEncoder());
                            System.out.println("111");
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (6)
            f.channel().writeAndFlush("hello server");
            f.channel().writeAndFlush("\r\n");
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
            Object result = f.channel().attr(AttributeKey.valueOf("sssss")).get();
            System.out.println("获取服务端返回的数据===" + result.toString());

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
