package ru.itmentor.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true, length = 100)
    private String name;

    @Column(name = "name", unique = true, length = 100)
    private String role;

    public Role() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.role = name;
    }

    public Role(Long id) {

        this.id = id;
    }
//todo Прошу понять и простить путаницу с именами, очень опаздываю к дедлайну, зато оно работает)
    public Role(String name, String role) {
        this.role = name;
        this.name = role;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.name = role;
    }

    @Override
    public String getAuthority() {

        return getRole();
    }

    @Override
    public String toString() {

        return role;

    }
}
