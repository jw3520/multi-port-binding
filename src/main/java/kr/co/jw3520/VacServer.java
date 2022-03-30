package kr.co.jw3520;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import kr.co.jw3520.service.VacServerInitializer;

import java.util.HashMap;
import java.util.Map;

public class VacServer {
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;

    public void start() {
        try {
            String arrVanType[] = VacServerConf.getInstance().getArrVanType();
            Map<String, ServerBootstrap> bootstraps = new HashMap<>();
            Map<String, ChannelFuture> channelFutures = new HashMap<>();

            for(String vanType : arrVanType) {
                bootstraps.put(vanType, new ServerBootstrap());
            }

            if(System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0) {
                bossGroup = new EpollEventLoopGroup(1);
                workerGroup = new EpollEventLoopGroup();
                for(String vanType : arrVanType) {
                    bootstraps.get(vanType).channel(EpollServerSocketChannel.class).group(bossGroup, workerGroup);
                }
            } else {
                bossGroup = new NioEventLoopGroup(1);
                workerGroup = new NioEventLoopGroup();
                for(String vanType : arrVanType) {
                    bootstraps.get(vanType).channel(NioServerSocketChannel.class).group(bossGroup, workerGroup);
                }
            }

            for(String vanType : arrVanType) {
                bootstraps.get(vanType).handler(new LoggingHandler(LogLevel.INFO));
                bootstraps.get(vanType).childHandler(new VacServerInitializer(vanType));
                channelFutures.put(vanType, bootstraps.get(vanType).bind(VacServerConf.getInstance().getPort(vanType)));
            }

            //Wait until the server socket is closed.
            for(String vanType : arrVanType) {
                channelFutures.get(vanType).channel().closeFuture().sync();
            }

        } catch (InterruptedException e) {
            //alarm & logging
        } catch (Exception e) {
            //alarm & logging
        }
    }

    public void stop() {
        if(workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if(bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        //alarm & logging
    }

    public static void main(String[] args) throws Exception {
        VacServer server = null;
        try {
            server = new VacServer();
            server.start();
        } catch (Exception e) {
            if(server != null) {
                server.stop();
            }
        }
    }
}
