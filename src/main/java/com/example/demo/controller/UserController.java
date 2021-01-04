package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"API для пользователей"}, description = "Получении информации о пользователях")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping
    @ApiOperation(value = "Запрос всех пользователей")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Добавление пользователя")
    public void addUser(@RequestBody User user) {
//        userDao.save(user);
        userDao.findById(user.getId()).orElseGet(() -> userDao.save(user)); // вызывыем только если не нашли
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получение пользователя")
    public User getUser(@PathVariable int id) {
        return userDao.findById(id).orElse(null);
//        return userDao.findUserById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Изменение пользователя")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        userDao.save(user);
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Поиск пользователей по имени")
    public List<User> getUsersByName(@PathVariable String name) {
        return userDao.findByName(name);
    }

    @GetMapping("/surname/{surname}")
    @ApiOperation(value = "Поиск пользователей по фамилии")
    public List<User> getUsersBySurname(@PathVariable String surname) {
        return userDao.findBySurnameMy(surname);
    }
}
