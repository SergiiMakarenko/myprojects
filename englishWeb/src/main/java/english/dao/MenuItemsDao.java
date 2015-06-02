package english.dao;

import english.domain.Menu;
import english.domain.MenuItems;

import java.util.List;

/**
 * Created by serg on 10.05.15.
 */
public interface MenuItemsDao {
    Long createMenuItems(String menuItems, String menuItemsCode, Menu menu);
    List<MenuItems> findAllMenuItems();
    MenuItems getMenuItemsById(Long id);
    List<MenuItems> getMenuItemsByMenu(Menu menu);
    List<MenuItems> getMenuItemsByPortion(int portion, int startFrom);
    boolean editMenuItems(Long menuItemsId, String menuItems, String menuItemsCode, Menu menu);
}
