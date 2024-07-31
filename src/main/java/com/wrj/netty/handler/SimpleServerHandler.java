package com.wrj.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.wrj.netty.util.Response;
import com.wrj.netty.handler.param.ServerRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * @ClassName SimpleServerHandler
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-09 11:07
 * @Version 1.0
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
       //ctx.channel().writeAndFlush("is ok junjun\r\n");
        ServerRequest request = JSONObject.parseObject(msg.toString(), ServerRequest.class);

        Response resp = new Response();
        resp.setId(request.getId());
        resp.setResult("is ok");
        ctx.channel().writeAndFlush(JSONObject.toJSONString(resp));
        ctx.channel().writeAndFlush("\r\n");

        ctx.channel().close();
    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("读空闲=====");
                ctx.channel().close();
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println("写空闲=====");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println("读写空闲=====");
                ctx.channel().writeAndFlush("ping\r\n");
            }
        }
    }

}
