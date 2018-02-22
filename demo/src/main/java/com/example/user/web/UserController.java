package com.example.user.web;

import com.example.user.domain.model.User;
import com.example.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    /**
     * 添加用户
     * @param user 用户
     */
    @PostMapping("/users")
    public Integer addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
