package com.example.emitter.web;

import com.example.emitter.domain.service.EmitterService;
import com.example.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Instant;

/**
 * 服务端事件推送
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sse")
public class EmitterController {

    private final EmitterService emitterService;

    /**
     * 注册监听者
     */
    @GetMapping("/connect")
    public SseEmitter connect(@AuthenticationPrincipal User user) {
        log.info("普通推送通道连接-注册监听者，当前用户：{}, 时间：{}", user.getId(), Instant.now().toEpochMilli());
        return emitterService.connect(user.getId());
    }

    /**
     * 向监听者发送消息
     * @param userId 监听用户id
     * @param message 消息内容
     */
    @PostMapping("/send")
    public void sendMsg(@RequestParam Integer userId, @RequestParam String message) {
        emitterService.sendMsg(userId, message);
    }


}
