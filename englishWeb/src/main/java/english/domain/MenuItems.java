package english.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by serg on 10.05.15.
 */
@Entity
@Table(name = "MENUITEMS")
public class MenuItems {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "MENUITEMS_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Menu_ID")
    private Long menuItemsId;

    @Column(name = "MENUITEMS")
    private String menuItems;

    @Column(name = "MENUITEMSCODE")
    private String menuItemsCode;

    @ManyToOne
    private Menu menu;

    public MenuItems(){}

    public MenuItems(String menuItems, String menuItemsCode, Menu menu) {
        this.menuItems = menuItems;
        this.menuItemsCode = menuItemsCode;
        this.menu = menu;
    }

    public Long getMenuItemsId() {
        return menuItemsId;
    }

    public void setMenuItemsId(Long menuItemsId) {
        this.menuItemsId = menuItemsId;
    }

    public String getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(String menuItems) {
        this.menuItems = menuItems;
    }

    public String getMenuItemsCode() {
        return menuItemsCode;
    }

    public void setMenuItemsCode(String menuItemsCode) {
        this.menuItemsCode = menuItemsCode;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItems menuItems1 = (MenuItems) o;

        if (menu != null ? !menu.equals(menuItems1.menu) : menuItems1.menu != null) return false;
        if (menuItems != null ? !menuItems.equals(menuItems1.menuItems) : menuItems1.menuItems != null) return false;
        if (menuItemsCode != null ? !menuItemsCode.equals(menuItems1.menuItemsCode) : menuItems1.menuItemsCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuItems != null ? menuItems.hashCode() : 0;
        result = 31 * result + (menuItemsCode != null ? menuItemsCode.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItems{" +
                "menuItemsId=" + menuItemsId +
                ", menuItems='" + menuItems + '\'' +
                ", menuItemsCode='" + menuItemsCode + '\'' +
                ", menu=" + menu.getMenuCategory() +
                '}';
    }
}
