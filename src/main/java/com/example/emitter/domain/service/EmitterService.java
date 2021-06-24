package com.example.emitter.domain.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 事件推送service
 */
public interface EmitterService {

    /**
     * 注册监听者
     * @param userId 用户id
     */
    SseEmitter connect(Integer userId);

    /**
     * 向监听者发送消息
     * @param userId 用户id
     * @param message 消息内容
     */
    void sendMsg(Integer userId, String message);
}
