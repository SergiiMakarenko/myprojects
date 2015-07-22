package english.dao.interfaces;

import english.domain.Role;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface RoleDao {
    Long createRole(String roleName);
    List<Role> findAllRole();
    Role getRoleById(Long id);
    Role getRoleByName(String roleName);
    List<Role> getRolesByPortion(int portion, int startFrom);
}
