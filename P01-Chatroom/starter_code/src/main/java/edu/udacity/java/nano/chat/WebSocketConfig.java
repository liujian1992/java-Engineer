package edu.udacity.java.nano.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
/**
 * ServerEndpointExporter 是由Spring官方提供的标准实现，
 * 用于扫描ServerEndpointConfig配置类和@ServerEndpoint注解实例。
 */
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
