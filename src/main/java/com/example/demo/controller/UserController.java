package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
//        userDao.save(user);
        userDao.findById(user.getId()).orElseGet(() -> userDao.save(user)); // вызывыем только если не нашли
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userDao.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        userDao.save(user);
    }
}
