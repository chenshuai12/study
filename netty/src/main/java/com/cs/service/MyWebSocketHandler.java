package com.cs.service;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;


public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channelGroup;

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    /**
     * 客户端与服务端建立连接的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("与客户端建立连接，通道开启！");
        // 添加到channelGroup通道组
        channelGroup.add(ctx.channel());
    }

    /**
     * 客户端与服务器关闭连接的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("与客户端断开连接，通道关闭");
        channelGroup.remove(ctx.channel());
    }

    /**
     * 服务器接受客户端的数据信息
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("服务器收到的数据：" + textWebSocketFrame.text());
        this.sendMessage(channelHandlerContext);
    }

    /**
     * 给固定的人发消息
     * @param ctx
     */
    private void sendMessage(ChannelHandlerContext ctx){
        String message = "您好, " + ctx.channel().localAddress() + "给固定的人发消息";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }
    /**
     * 发送群消息，此时其他客户端也能收到群消息
     */
    private void sendAllMessage() {
        String message = "我是服务器，这里发送的是群消息";
        channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }
}
