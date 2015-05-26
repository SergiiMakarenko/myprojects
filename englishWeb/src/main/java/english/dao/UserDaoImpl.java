package english.dao;

import english.domain.Role;
import english.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by serg on 03.04.15.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long createUser(String login, String pass, Role role) {
        User userTest = (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userLogin",login))
                .uniqueResult();
        if(userTest==null){

            Long userId = (Long) factory.getCurrentSession().save(new User(login, pass, role));
            Set<User> userSet = new HashSet<>();
            List<User> users = factory.getCurrentSession().createCriteria(User.class)
                    .add(Restrictions.eq("userRole",role))
                    .list();
            userSet.addAll(users);
            role.setUserSet(userSet);
            role = (Role) factory.getCurrentSession().merge(role);
            factory.getCurrentSession().update(role);
            return userId;
        }
        System.out.println("user with the same login contains in base");
        return null;
    }

    @Override
    public boolean updateUser(String id, String login, String pass, Role role) {
        User user = (User) factory.getCurrentSession().get(User.class,Long.parseLong(id));
        User userTest = (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userLogin", login))
                .uniqueResult();

        if(userTest!=null && userTest.getUserId()!=Long.parseLong(id)) {
            return false;
        }
            Role roleBefore = user.getUserRole();
            user.setUserLogin(login);
            user.setUserPassword(pass);
            user.setUserRole(role);
            factory.getCurrentSession().update(user);
            if(!roleBefore.equals(role)){
                List<User> usersBefore = factory.getCurrentSession().createCriteria(User.class)
                        .add(Restrictions.eq("userRole", roleBefore))
                        .list();
                Set<User> userSet = new HashSet<>();
                userSet.addAll(usersBefore);
                roleBefore.setUserSet(userSet);
                roleBefore = (Role) factory.getCurrentSession().merge(roleBefore);
                factory.getCurrentSession().update(roleBefore);

                List<User> users = factory.getCurrentSession().createCriteria(User.class)
                        .add(Restrictions.eq("userRole", role))
                        .list();
                userSet = new HashSet<>();
                userSet.addAll(users);
                role.setUserSet(userSet);
                role = (Role) factory.getCurrentSession().merge(role);
                factory.getCurrentSession().update(role);
            }
            return true;
    }

    @Override
    public List<User> findAllUsers() {
        return factory.getCurrentSession().createCriteria(User.class)
                .list();
    }

    @Override
    public boolean checkUser(String login, String password) {
        User userTest = (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userLogin",login))
                .add(Restrictions.eq("userPassword",password))
                .uniqueResult();
        if(userTest==null) {
            return false;
        }
        return true;
    }

    @Override
    public User readUser(String login, String password) {
        return (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userLogin",login))
                .add(Restrictions.eq("userPassword",password))
                .uniqueResult();
    }

    @Override
    public User getUserById(Long id) {
        return (User) factory.getCurrentSession().get(User.class,id);
    }

    @Override
    public List<User> getUsersByPortion(String portion, String startFrom) {
        return factory.getCurrentSession().createCriteria(User.class)
                .setFirstResult(Integer.parseInt(startFrom))
                .setMaxResults(Integer.parseInt(portion))
                        .list();
    }

    @Override
    public User getUserByLogin(String userLogin) {
        return (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userLogin",userLogin))
                .uniqueResult();
    }

    @Override
    public boolean updateUser(User user) {
        User userTest = (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userLogin", user.getUserLogin()))
                .uniqueResult();

        if(userTest!=null && userTest.getUserId()!=user.getUserId()) {
            return false;
        }
        user = (User) factory.getCurrentSession().merge(user);
        factory.getCurrentSession().update(user);
        return true;

    }
}
