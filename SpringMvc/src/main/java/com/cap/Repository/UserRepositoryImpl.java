package com.cap.Repository;

import com.cap.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private  List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        users.add(new User(1L, "John Doe", "john@example.com"));
        users.add(new User(2L, "John Don", "johndon@example.com"));

    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
