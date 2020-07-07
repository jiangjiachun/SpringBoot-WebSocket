package com.jjc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfigTest implements WebSocketConfigurer {

	@Bean
	public WebSocketHandler myHandler() {
		return new MyHandler();
	}

	 
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/push")
				.addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*");
		
	}

}
