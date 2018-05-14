package com.example.user.web;

import com.example.user.domain.model.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.domain.service.UserService;
import com.example.user.web.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 创建用户
     * @param createUserDTO 用户创建dto
     */

    @PostMapping("/users")
    public Integer addUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        User user = createUserDTO.extractUser();
        Assert.isTrue(!userRepository.existsByUsername(user.getUsername()), "user already exist");
        return userService.addUser(user);
    }
    
}
