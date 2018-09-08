package com.example.userbindinfo.web;

import com.example.user.domain.model.User;
import com.example.userbindinfo.domain.model.UserBindInfo;
import com.example.userbindinfo.domain.repository.UserBindInfoRepository;
import com.example.utils.CharacterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户绑定信息控制层
 */
@RestController
@RequiredArgsConstructor
public class UserBindInfoController {

    private final UserBindInfoRepository userBindInfoRepository;

    //绑定码长度
    private static final int BIND_CODE_LENGTH = 6;

    /**
     * 生成自己的绑定码
     * @return 绑定码
     */
    @PostMapping("/userBindInfoGenerates")
    @Transactional
    public String generateUserBindInfo(@AuthenticationPrincipal User user) {
        UserBindInfo userBindInfo = userBindInfoRepository.findOneByUserId(user.getId());
        String code = CharacterUtils.getRandomString(BIND_CODE_LENGTH);
        while (userBindInfoRepository.existsByCode(code)) {
            code = CharacterUtils.getRandomString(BIND_CODE_LENGTH);
        }
        if (userBindInfo == null) {
            userBindInfoRepository.save(new UserBindInfo(user.getId(), code));
        }else {
            userBindInfo.setCode(code);
        }
        return code;
    }

    /**
     * 获取自己的绑定码
     */
    @GetMapping("/userBindInfos")
    public String getMyUserBindInfos(@AuthenticationPrincipal User user) {
        UserBindInfo userBindInfo = userBindInfoRepository.findOneByUserId(user.getId());
        return userBindInfo == null ? null : userBindInfo.getCode();
    }
}
