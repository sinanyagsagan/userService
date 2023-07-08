package io.synansoft.userservice.service;

import io.synansoft.userservice.model.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
