package com.anf.blog.netty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anf.blog.SpringblogApplication;
import com.anf.blog.util.RedisUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//	@Autowired
//	private RedisUtil redisUtil;
	//当有客户端连接时，该方法自动调用
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel in = ctx.channel();
		channels.add(in);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel out = ctx.channel();
		String str = (String) SpringblogApplication.cacheMap.get(out.id());
		ApplicationContext c = (ApplicationContext) SpringblogApplication.cacheMap.get("beans");
		RedisUtil redisUtil = (RedisUtil) c.getBean("redisUtil");
		String[] changeTo = str.split(":");
		redisUtil.remove("status:"+changeTo[1]);
		SpringblogApplication.cacheMap.remove(str);
		SpringblogApplication.cacheMap.remove(out.id());
		channels.remove(out);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		Channel msgIn = ctx.channel();//获得发送方的连接通道
		ApplicationContext c = (ApplicationContext) SpringblogApplication.cacheMap.get("beans");
		RedisUtil redisUtil = (RedisUtil) c.getBean("redisUtil");
		if(msg.text().startsWith("Connect")) {
			SpringblogApplication.cacheMap.put(msg.text(), msgIn);
			SpringblogApplication.cacheMap.put(msgIn.id(),msg.text());
			String userId = (String) msg.text().subSequence(8, msg.text().length());
			redisUtil.set("status:"+userId,"online");
			@SuppressWarnings("unchecked")
			List<JSONObject> messages = (List<JSONObject>)redisUtil.get("message:"+userId);
			if(messages!=null) {
				redisUtil.remove("message:"+userId);
				for(JSONObject mess:messages) {
					messageSend(mess,userId);
				}
			}
		}else {
			JSONObject obj = JSON.parseObject(msg.text());
			String[] changeTo = obj.get("type").toString().split(":");
			if("friend".equals(changeTo[0])) {
				String mess = (String)redisUtil.get("status:"+changeTo[1]);
				if(!"online".equals(mess)) {
					@SuppressWarnings("unchecked")
					List<JSONObject> messages = (List<JSONObject>)redisUtil.get("message:"+changeTo[1]);
					messages = messages==null?new ArrayList<JSONObject>():messages;
					messages.add(obj);
					redisUtil.set("message:"+changeTo[1], messages);
				}else{
					messageSend(obj,changeTo[1]);
				}
			}
		}
	}

	private void messageSend(JSONObject obj, String userId) {
		HashMap<String,Object> m = new HashMap<String,Object>();
		Channel ch = (Channel) SpringblogApplication.cacheMap.get("Connect:"+userId);
		JSONObject data = (JSONObject) obj.get("data");
		JSONObject mine = (JSONObject)data.get("mine");
		JSONObject to = (JSONObject)data.get("to");
		m.put("username", mine.get("username"));
		m.put("avatar",mine.get("avatar"));
		m.put("id",mine.get("id").toString());
		m.put("type","friend");
		m.put("mine", false);
		m.put("fromid",mine.get("id").toString());
		m.put("timestamp",new Date().getTime());
		m.put("content", mine.get("content"));
		JSONObject json = new JSONObject(m);
		System.out.println(json.toJSONString());
		ch.writeAndFlush(new TextWebSocketFrame(json.toJSONString()));

		
	}

}
