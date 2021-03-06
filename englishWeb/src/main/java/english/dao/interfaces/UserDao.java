package english.dao.interfaces;

import english.domain.Role;
import english.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Repository
public interface UserDao {
    Long createUser(String login, String pass, Role role);
    boolean updateUser(String userId, String login, String pass, Role role);
    List<User> findAllUsers();
    boolean checkUser(String login, String password);
    User readUser(String login, String password);
    User getUserById(Long id);
    List<User> getUsersByPortion(int portion, int startFrom);
    User getUserByLogin(String userLogin);
    boolean updateUser(User user);
}
