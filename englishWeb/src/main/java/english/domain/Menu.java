package english.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergii Makarenko
 * Entity of menu head
 */
@Entity
@Table(name = "MENUS")
public class Menu {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "MENU_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Menu_ID")
    private Long menuId;

    @Column(name = "MENUCATEGORY")
    private String menuCategory;

    @JsonIgnore
    @ManyToOne
    private Role role;

    @JsonIgnore
    @OneToMany
    private Set<MenuItems> menuItemsSet;

    public Menu(){}

    public Menu(String menuCategory, Role role) {
        this.menuCategory = menuCategory;
        this.role = role;
        this.menuItemsSet = new HashSet<>();
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<MenuItems> getMenuItemsSet() {
        return menuItemsSet;
    }

    public void setMenuItemsSet(Set<MenuItems> menuItemsSet) {
        this.menuItemsSet = menuItemsSet;
    }



    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuCategory='" + menuCategory + '\'' +
                ", role=" + role.getRoleName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return !(menuCategory != null ? !menuCategory.equals(menu.menuCategory) : menu.menuCategory != null);
    }

    @Override
    public int hashCode() {
        return menuCategory != null ? menuCategory.hashCode() : 0;
    }
}
