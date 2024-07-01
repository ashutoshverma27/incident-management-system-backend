package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dao.UserDao;
import com.ims.entity.User;


@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    @Transactional
    public User createUser(User user) {
        return userDao.save(user);
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public User getUserById(Long id) {
        return userDao.findById(id);        
    }

    public User authenticate(String email, String password) {
        List<User> users = userDao.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }
}
