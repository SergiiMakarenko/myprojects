package english.dao.interfaces;

import english.domain.Menu;
import english.domain.Role;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface MenuDao {
    Long createMenu(String menuCategory, Role role);
    List<Menu> findAllMenu();
    Menu getMenuById(Long id);
    List<Menu> getMenuByRole(Role role);
    List<Menu> getMenusByPortion(int portion, int startFrom);
    boolean editMenu(Long menuId, String menuCategory, Role role);
}
