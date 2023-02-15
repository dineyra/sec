package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Lazy
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Transactional(readOnly = false)
    public User getUserByEmail(String email) {

        return userDao.getUserByEmail(email);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional(readOnly = false)
    public User getUserById(Long id) {

        return userDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getUserByEmail(email);
    }
}