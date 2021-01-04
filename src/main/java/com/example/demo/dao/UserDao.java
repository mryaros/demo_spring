package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
//    @Query(value = "SELECT * FROM t_users tu WHERE tu.surname = :#{#surname}", nativeQuery = true)
//    @Query(value = "select u from User u where u.surname = :surname")
    @Query(value = "SELECT * FROM t_users tu WHERE tu.surname = :surname", nativeQuery = true)
    List<User> findBySurnameMy(String surname);

//    @Query(value = "select getUserById(:id)", nativeQuery = true)
//    User findUserById(int id);
}
