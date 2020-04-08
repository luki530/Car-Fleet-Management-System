package pl.com.company.auth.service;

import pl.com.company.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
