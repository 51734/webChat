package com.anf.blog.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebChatServerInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast(new HttpServerCodec())  //将请求和应答消息编码或者解码为http协议的消息
				.addLast(new HttpObjectAggregator(1024*64))
				.addLast(new ChunkedWriteHandler())
				.addLast(new HttpRequestHandler("/chat"))
				.addLast(new WebSocketServerProtocolHandler("/chat"))
				.addLast(new TextWebSocketFrameHandler());//负责向客户端发送html页面
	}
	

}
