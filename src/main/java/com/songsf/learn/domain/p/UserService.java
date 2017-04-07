package com.songsf.learn.domain.p;

import java.util.List;

/**
 * Created by songsf on 2017/4/7.
 */
public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
}
