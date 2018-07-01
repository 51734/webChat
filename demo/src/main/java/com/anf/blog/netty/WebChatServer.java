package com.anf.blog.netty;

import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Service
public class WebChatServer {
	public void start() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(bossGroup,workerGroup)
					   .channel(NioServerSocketChannel.class)
					   .childHandler(new WebChatServerInit())
					   .option(ChannelOption.SO_BACKLOG, 128)
					   .childOption(ChannelOption.SO_KEEPALIVE, true);
			
			
			ChannelFuture future = boot.bind(8081).sync();
			System.out.println("[系统消息]：服务器已连接");
			future.channel().closeFuture().sync();
			System.out.println("[系统消息]：服务器已关闭");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
}
