package com.example.emitter.domain.service.impl;

import com.example.emitter.domain.service.EmitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmitterServiceImpl implements EmitterService {

    private Map<Integer, SseEmitter> emitterMap = new HashMap<>();


    @Override
    public SseEmitter connect(Integer userId) {
        //删除老的监听者
        emitterMap.remove(userId);
        // 默认30秒超时,设置为0L则永不超时,单位为毫秒
        SseEmitter sseEmitter = new SseEmitter(300*1000L);
        // 注册回调
//        sseEmitter.onCompletion(this.completionCallback(sseEmitter));
//        sseEmitter.onTimeout(this.timeoutCallback(sseEmitter));
        //增加新的监听者
        emitterMap.put(userId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void sendMsg(Integer userId, String message) {

        SseEmitter sseEmitter = this.emitterMap.get(userId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(message);
            } catch (IOException e) {
                log.error("发送信息失败：{}", e.getMessage());
            }
        }
    }
}
