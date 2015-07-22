package english.service;

import english.domain.Menu;
import english.domain.MenuItems;
import english.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface MenuService {

    Long createMenu(String menuCategory, Role role);
    List<Menu> findAllMenu();
    Menu getMenuById(Long id);
    List<Menu> getMenuByRole(Role role);
    List<Menu> getMenusByPortion(int portion, int startFrom);
    boolean editMenu(Long menuId, String menuCategory, Role role);

    Long createMenuItems(String menuItems, String menuItemsCode, Menu menu);
    List<MenuItems> findAllMenuItems();
    MenuItems getMenuItemsById(Long id);
    List<MenuItems> getMenuItemsByMenu(Menu menu);
    List<MenuItems> getMenuItemsByPortion(int portion, int startFrom);
    boolean editMenuItems(Long menuItemsId, String menuItems, String menuItemsCode, Menu menu);

}
