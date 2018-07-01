package com.anf.blog.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest>{
	
	private final String ChatUri;
	public HttpRequestHandler(String ChatUri) {
		this.ChatUri = ChatUri;
	}
	
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		ctx.fireChannelRead(request.retain());
	}
}