package com.wrj.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.wrj.netty.client.DefaultFuture;
import com.wrj.netty.util.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName SimpleClientHandler
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-09 17:16
 * @Version 1.0
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO: Auto-generated method stub
        if ("ping".equals(msg.toString())) {
            ctx.channel().writeAndFlush("ping\r\n");
            return;
        }
        System.out.println(msg.toString());
//        ctx.channel().attr(AttributeKey.valueOf("sssss")).set(msg);
//        ctx.channel().close();
        Response response = JSONObject.parseObject(msg.toString(), Response.class);
        DefaultFuture.receive(response);

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // TODO: Auto-generated method stub
    }
}
