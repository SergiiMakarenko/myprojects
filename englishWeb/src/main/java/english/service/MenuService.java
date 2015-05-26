package english.service;

import english.domain.Menu;
import english.domain.MenuItems;
import english.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by serg on 10.05.15.
 */
public interface MenuService {

    Long createMenu(String menuCategory, Role role);
    List<Menu> findAllMenu();
    Menu getMenuById(Long id);
    List<Menu> getMenuByRole(Role role);
    List<Menu> getMenusByPortion(String portion, String startFrom);
    boolean editMenu(Long menuId, String menuCategory, Role role);

    Long createMenuItems(String menuItems, String menuItemsCode, Menu menu);
    List<MenuItems> findAllMenuItems();
    MenuItems getMenuItemsById(Long id);
    List<MenuItems> getMenuItemsByMenu(Menu menu);
    List<MenuItems> getMenuItemsByPortion(String portion, String startFrom);
    boolean editMenuItems(Long menuItemsId, String menuItems, String menuItemsCode, Menu menu);

}
