package com.jjc.websocket;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.HtmlUtils;

import com.jjc.config.MyHandler;

@Controller
public class GreetingController {

	@Resource
	private MyHandler myHandler;

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		return "index.html";
	}
	
	@GetMapping("/msg")
	public String msg(HttpServletRequest request) {
		return "msg.html";
	}
	
	@GetMapping("sendMsg")
	@ResponseBody
	public boolean sendMsg(HttpServletRequest request) {
		myHandler.getSessions().forEach(session -> {
			/*String id = (String) session.getAttributes().get("id");
			if(id.equals("123456")) {*/
				WebSocketMessage<String> webSocketMessage = new TextMessage("Hello,77777");
				try {
					session.sendMessage(webSocketMessage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/* } */ 
		});
		return true;
	} 
}