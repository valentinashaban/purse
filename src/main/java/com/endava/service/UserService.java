package com.endava.service;

import com.endava.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by vsaban on 4/7/2017.
 */
@Service
public interface UserService {

    User saveUser(User user);

    void deleteUser(User user);

    User updateUser(User user);

    User getUserById(Long id);

    boolean exists(User user);
}
