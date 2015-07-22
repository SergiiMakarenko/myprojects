package english.dao.implementation;

import english.dao.interfaces.RoleDao;
import english.domain.Role;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long createRole(String roleName) {
        Role testRole = (Role) factory.getCurrentSession().createCriteria(Role.class)
                .add(Restrictions.eq("roleName",roleName))
                .uniqueResult();
        if(testRole==null) {
            return (Long) factory.getCurrentSession().save(new Role(roleName));
        }
        return null;
    }

    @Override
    public List<Role> findAllRole() {
        return factory.getCurrentSession().createCriteria(Role.class)
                .list();
    }

    @Override
    public Role getRoleById(Long id) {
        return (Role) factory.getCurrentSession().get(Role.class,id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return (Role) factory.getCurrentSession().createCriteria(Role.class)
                .add(Restrictions.eq("roleName",roleName))
                .uniqueResult();
    }

    @Override
    public List<Role> getRolesByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(Role.class)
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .list();
    }

}
