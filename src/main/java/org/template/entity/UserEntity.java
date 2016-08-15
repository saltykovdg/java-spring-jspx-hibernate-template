package org.template.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = UserEntity.TABLE_NAME, indexes = {@Index(columnList = UserEntity.Columns.EMAIL)})
public class UserEntity extends AbstractEntity {
    public static final String TABLE_NAME = "security_users";

    public static class Columns extends AbstractEntity.Columns {
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String BLOCKED = "blocked";
    }

    @Column(name = Columns.EMAIL)
    @NotEmpty
    private String email;

    @Column(name = Columns.PASSWORD)
    @NotEmpty
    private String password;

    @Column(name = Columns.BLOCKED)
    private Boolean blocked;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "security_users_roles",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
    private List<RoleEntity> roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}