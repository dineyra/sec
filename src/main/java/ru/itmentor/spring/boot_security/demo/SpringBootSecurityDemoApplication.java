package ru.itmentor.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itmentor.spring.boot_security.demo.dao.RoleDao;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceImpl;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        settings(context);
    }

    private static void settings(ApplicationContext context) {
        UserDao userDao = context.getBean(UserDao.class);
        RoleDao roleDao = context.getBean(RoleDao.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);
        User user = null;//new User("admin", "admin", 35, "admin@mail.ru", "admin");
        try {
            user = userDao.getUserByEmail("admin@mail.ru");
        } catch (NoResultException | EmptyResultDataAccessException e) {
            Role role = new Role("ROLE_ADMIN");
            try {
                role = roleDao.getRoleByName("ROLE_ADMIN");
            } catch (NoResultException | EmptyResultDataAccessException ex) {
                roleDao.add(role);
            }
            Set<Role> rolesSet = new HashSet<>();
            rolesSet.add(role);
            user.setRoles(rolesSet);
            userService.addUser(user);
        }
    }
}
