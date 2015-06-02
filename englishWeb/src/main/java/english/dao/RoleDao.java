package english.dao;

import english.domain.Role;

import java.util.List;

/**
 * Created by serg on 29.04.15.
 */
public interface RoleDao {
    Long createRole(String roleName);
    List<Role> findAllRole();
    Role getRoleById(Long id);
    Role getRoleByName(String roleName);
    List<Role> getRolesByPortion(int portion, int startFrom);
}
