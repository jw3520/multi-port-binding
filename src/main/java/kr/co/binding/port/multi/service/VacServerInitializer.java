package kr.co.binding.port.multi.service;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class VacServerInitializer extends ChannelInitializer<SocketChannel> {
    private final String vanType;

    public VacServerInitializer(String vanType) {
        this.vanType = vanType;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        switch (vanType) {
            case "a":
                //add a pipeLine
                break;
            case "b":
                //add b pipeLine
                break;
            default:
                new Exception("Wrong vanType : " + vanType);
        }
        pipeline.addLast("CommonHandlers", new ChannelHandler() {
            @Override
            public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

            }

            @Override
            public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

            }
        });
    }
}
