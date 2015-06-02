package english.service;


import english.dao.*;
import english.domain.IrregularVerb;
import english.domain.Role;
import english.domain.Word;
import english.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by serg on 03.04.15.
 */
@Service

public class UserServiceImpl implements UserService  {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;


    @Override
    @Transactional(readOnly = true)
    public boolean checkUser(String login, String password) {
        return userDao.checkUser(login, password);
    }

    @Override
    @Transactional(readOnly = true)
    public User readUser(String login, String password) {
        return userDao.readUser(login, password);
    }

    @Override
    @Transactional
    public Long createUser(String login, String pass, Role role) {
        return userDao.createUser(login, pass, role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public boolean updateUser(String userId, String login, String pass, Role role) {
        return userDao.updateUser(userId, login, pass, role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByPortion(int portion, int startFrom) {
        return userDao.getUsersByPortion(portion, startFrom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRolesByPortion(int portion, int startFrom) {
        return roleDao.getRolesByPortion(portion, startFrom);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String userLogin) {
        return userDao.getUserByLogin(userLogin);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public Long createRole(String roleName) {
        return roleDao.createRole(roleName);
    }

}
