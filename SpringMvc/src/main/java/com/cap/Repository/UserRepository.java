package com.cap.Repository;

import com.cap.Model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    void save (User usr);
}
