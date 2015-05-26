package english.service;

import english.dao.MenuDao;
import english.dao.MenuItemsDao;
import english.domain.Menu;
import english.domain.MenuItems;
import english.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by serg on 10.05.15.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuItemsDao menuItemsDao;

    @Override
    @Transactional
    public Long createMenu(String menuCategory, Role role) {
        return menuDao.createMenu(menuCategory, role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }

    @Override
    @Transactional(readOnly = true)
    public Menu getMenuById(Long id) {
        return menuDao.getMenuById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getMenuByRole(Role role) {
        return menuDao.getMenuByRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getMenusByPortion(String portion, String startFrom) {
        return menuDao.getMenusByPortion(portion, startFrom);
    }

    @Override
    @Transactional
    public boolean editMenu(Long menuId, String menuCategory, Role role) {
        return menuDao.editMenu(menuId, menuCategory, role);
    }

    @Override
    @Transactional
    public Long createMenuItems(String menuItems, String menuItemsCode, Menu menu) {
        return menuItemsDao.createMenuItems(menuItems, menuItemsCode, menu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItems> findAllMenuItems() {
        return menuItemsDao.findAllMenuItems();
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItems getMenuItemsById(Long id) {
        return menuItemsDao.getMenuItemsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItems> getMenuItemsByMenu(Menu menu) {
        return menuItemsDao.getMenuItemsByMenu(menu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItems> getMenuItemsByPortion(String portion, String startFrom) {
        return menuItemsDao.getMenuItemsByPortion(portion, startFrom);
    }

    @Override
    @Transactional
    public boolean editMenuItems(Long menuItemsId, String menuItems, String menuItemsCode, Menu menu) {
        return menuItemsDao.editMenuItems(menuItemsId, menuItems, menuItemsCode, menu);
    }
}
