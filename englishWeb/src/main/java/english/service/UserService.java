package english.service;

import english.domain.IrregularVerb;
import english.domain.Role;
import english.domain.Word;
import english.domain.User;

import java.util.List;

/**
 * Created by serg on 03.04.15.
 */
public interface UserService {
    Long createRole(String roleName);
    boolean checkUser(String login, String password);
    User readUser(String login, String password);
    Long createUser(String login, String pass, Role role);
    List<User> findAllUsers();
    List<Role> findAllRole();
    User getUserById(Long id);
    boolean updateUser(String userId, String login, String pass, Role role);
    Role getRoleById(Long id);
    Role getRoleByName(String roleName);
    List<User> getUsersByPortion(String portion, String startFrom);
    List<Role> getRolesByPortion(String portion, String startFrom);
    User getUserByLogin(String userLogin);
    boolean updateUser(User user);
}