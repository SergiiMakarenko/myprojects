package english.dao;

import english.domain.Menu;
import english.domain.MenuItems;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by serg on 10.05.15.
 */
@Repository
public class MenuItemsDaoImpl implements MenuItemsDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long createMenuItems(String menuItems, String menuItemsCode, Menu menu) {
        MenuItems menuItemsTest = (MenuItems) factory.getCurrentSession().createCriteria(MenuItems.class)
                .add(Restrictions.eq("menuItems",menuItems))
                .add(Restrictions.eq("menuItemsCode",menuItemsCode))
                .add(Restrictions.eq("menu",menu))
                .uniqueResult();
        if(menuItemsTest==null){
            Long newMenuItemsId = (Long) factory.getCurrentSession().save(new MenuItems(menuItems,menuItemsCode,menu));
            Set<MenuItems> menuSet = new HashSet<>();
            List<MenuItems> menuItemses = getMenuItemsByMenu(menu);
            menuSet.addAll(menuItemses);
            menu.setMenuItemsSet(menuSet);
            menu = (Menu) factory.getCurrentSession().merge(menu);
            factory.getCurrentSession().update(menu);
            return newMenuItemsId;
        }
        return null;
    }

    @Override
    public List<MenuItems> findAllMenuItems() {
        return factory.getCurrentSession().createCriteria(MenuItems.class)
                .list();
    }

    @Override
    public MenuItems getMenuItemsById(Long id) {
        return (MenuItems) factory.getCurrentSession().get(MenuItems.class,id);
    }

    @Override
    public List<MenuItems>  getMenuItemsByMenu(Menu menu) {
        return factory.getCurrentSession().createCriteria(MenuItems.class)
                .add(Restrictions.eq("menu",menu))
                .list();
    }

    @Override
    public List<MenuItems> getMenuItemsByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(MenuItems.class)
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .addOrder(Order.asc("menu"))
                .list();
    }

    @Override
    public boolean editMenuItems(Long menuItemsId, String menuItems, String menuItemsCode, Menu menu) {
        MenuItems menuItemsTest = (MenuItems) factory.getCurrentSession().createCriteria(MenuItems.class)
                .add(Restrictions.eq("menuItems", menuItems))
                .add(Restrictions.eq("menuItemsCode",menuItemsCode))
                .add(Restrictions.eq("menu",menu))
                .uniqueResult();

        if(menuItemsTest!=null && menuItemsTest.getMenuItemsId()!=menuItemsId) {
            return false;
        }

        MenuItems menuItemsBefore = (MenuItems) factory.getCurrentSession().get(MenuItems.class,menuItemsId);
        Menu menuBefore = menuItemsBefore.getMenu();
        MenuItems menuItemsNew = menuItemsBefore;
        menuItemsNew.setMenu(menu);
        menuItemsNew.setMenuItems(menuItems);
        menuItemsNew.setMenuItemsCode(menuItemsCode);

        menuItemsNew = (MenuItems) factory.getCurrentSession().merge(menuItemsNew);
        factory.getCurrentSession().update(menuItemsNew);

        if(!menuBefore.equals(menu)){
            List<MenuItems> menusBefore = factory.getCurrentSession().createCriteria(MenuItems.class)
                    .add(Restrictions.eq("menu", menuBefore))
                    .list();
            Set<MenuItems> menuItemsSet = new HashSet<>();
            menuItemsSet.addAll(menusBefore);
            menuBefore.setMenuItemsSet(menuItemsSet);
            menuBefore = (Menu) factory.getCurrentSession().merge(menuBefore);
            factory.getCurrentSession().update(menuBefore);

            List<MenuItems> menus = factory.getCurrentSession().createCriteria(MenuItems.class)
                    .add(Restrictions.eq("menu", menu))
                    .list();
            menuItemsSet = new HashSet<>();
            menuItemsSet.addAll(menus);
            menu.setMenuItemsSet(menuItemsSet);
            menu = (Menu) factory.getCurrentSession().merge(menu);
            factory.getCurrentSession().update(menu);
        }
        return true;
    }
}
