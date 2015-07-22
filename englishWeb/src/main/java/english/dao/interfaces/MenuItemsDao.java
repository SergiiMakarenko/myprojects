package english.dao.interfaces;

import english.domain.Menu;
import english.domain.MenuItems;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface MenuItemsDao {
    Long createMenuItems(String menuItems, String menuItemsCode, Menu menu);
    List<MenuItems> findAllMenuItems();
    MenuItems getMenuItemsById(Long id);
    List<MenuItems> getMenuItemsByMenu(Menu menu);
    List<MenuItems> getMenuItemsByPortion(int portion, int startFrom);
    boolean editMenuItems(Long menuItemsId, String menuItems, String menuItemsCode, Menu menu);
}
