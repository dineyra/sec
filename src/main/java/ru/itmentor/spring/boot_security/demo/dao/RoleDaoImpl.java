package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        List<Role> roleList = entityManager.createQuery("select r from Role r ", Role.class).getResultList();
        return new HashSet<>(roleList);
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery(
                "SELECT r from Role r where r.role=:role", Role.class
        ).setParameter("role", role).getSingleResult();
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roleNames) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleNames) {
            roleSet.add(getRoleByName(role));
        }
        return roleSet;
    }

    @Override
    @Transactional
    public void add(Role role) {

        entityManager.persist(role);
    }

    @Override
    public void edit(Role role) {

        entityManager.merge(role);
    }

    @Override
    public Role getById(Long id) {

        return entityManager.find(Role.class, id);
    }
}