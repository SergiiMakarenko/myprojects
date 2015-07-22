package english.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergii Makarenko
 * Entity of users roles
 */
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Role_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLENAME")
    private String roleName;

    @JsonIgnore
    @OneToMany
    private Set<Menu> menuSet;

    @JsonIgnore
    @OneToMany
    private Set<User> userSet;

    public Role(){}

    public Role(String roleName) {
        this.roleName = roleName;
        this.userSet = new HashSet<>();
        this.menuSet = new HashSet<>();
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
