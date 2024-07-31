package com.wrj.netty.init;

import com.wrj.netty.constant.Constants;
import com.wrj.netty.factory.ZookeeperFactory;
import com.wrj.netty.handler.ServerHandler;
import com.wrj.netty.handler.SimpleServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServer
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-09 10:51
 * @Version 1.0
 */
@Component
public class NettyInitial implements ApplicationListener<ContextRefreshedEvent> {//这个才是最新的启动类
    //在Spring上下文刷新事件中，Spring会自动调用实现了ApplicationListener<ContextRefreshedEvent>接口的onApplicationEvent方法，从而启动你的Netty服务器。
    public void start()  {
        EventLoopGroup parentGroup= new NioEventLoopGroup();
        EventLoopGroup childGroup= new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup);//连接事件 读写事件
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)//允许通道
                    .childOption(ChannelOption.SO_KEEPALIVE, false)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {//这个是附加到服务端给客户端新建的channel上的
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(65535, Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new IdleStateHandler(60, 45, 20, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new ServerHandler());
                            ch.pipeline().addLast(new StringEncoder());

                        }
                    });
            int port=8081;
            int weight=1;
            System.out.println("Server is starting...");
            ChannelFuture f = bootstrap.bind(port).sync();
            CuratorFramework client = ZookeeperFactory.create();
            InetAddress netAddress = InetAddress.getLocalHost();
//            client.create()
//                    .withMode(CreateMode.EPHEMERAL)
//                    .forPath(Constants.SERVER_PATH + netAddress.getHostAddress());
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(Constants.SERVER_PATH +"/"+ netAddress.getHostAddress() + "#"+port+"#"+weight+"#");

//            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
//                    .forPath(Constants.SERVER_PATH +"/"+ netAddress.getHostAddress() + "#");
//            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
//                    .forPath(Constants.SERVER_PATH + "/" + netAddress.getHostAddress());

            System.out.println("Server started and listening on port 8081");
            f.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
         this.start();
    }
}
