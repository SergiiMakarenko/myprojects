package english.dao;

import english.domain.Menu;
import english.domain.Role;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Repository;

import java.awt.geom.RectangularShape;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by serg on 10.05.15.
 */
@Repository
public class MenuDaoImpl implements MenuDao {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long createMenu(String menuCategory, Role role) {
        Menu menuTest = (Menu) factory.getCurrentSession().createCriteria(Menu.class)
                .add(Restrictions.eq("menuCategory",menuCategory))
                .add(Restrictions.eq("role",role))
                .uniqueResult();
        if(menuTest!=null){
            return null;
        }
        Long newMenuId = (Long) factory.getCurrentSession().save(new Menu(menuCategory,role));
        Set<Menu> menuSet = new HashSet<>();
        //menuSet= (Set<Menu>) getMenuByRole(role);
        List<Menu> menus = getMenuByRole(role);
        menuSet.addAll(menus);
        role.setMenuSet(menuSet);
        role = (Role) factory.getCurrentSession().merge(role);
        factory.getCurrentSession().update(role);
        return newMenuId;
    }

    @Override
    public List<Menu> findAllMenu() {
        return factory.getCurrentSession().createCriteria(Menu.class)
                .list();
    }

    @Override
    public Menu getMenuById(Long id) {
        return (Menu) factory.getCurrentSession().get(Menu.class,id);
    }

    @Override
    public List<Menu> getMenuByRole(Role role) {
        return factory.getCurrentSession().createCriteria(Menu.class)
                .add(Restrictions.eq("role",role))
                .list();
    }

    @Override
    public List<Menu> getMenusByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(Menu.class)
                .setFirstResult(startFrom)
                .setMaxResults(portion)
                .list();
    }

    @Override
    public boolean editMenu(Long menuId, String menuCategory, Role role) {
        return false;
    }


}
