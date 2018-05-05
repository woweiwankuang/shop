package com.example.user.domain.service.impl;

import com.example.user.domain.model.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.domain.service.TokenRevokeService;
import com.example.user.domain.service.UserService;
import com.example.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final TokenRevokeService tokenRevokeService;

    @Override
    public Integer addUser(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        if (user.getId() != null) {
            tokenRevokeService.revoke(user.getUsername());
        }
        userRepository.save(user);
        return user.getId();
    }
}
