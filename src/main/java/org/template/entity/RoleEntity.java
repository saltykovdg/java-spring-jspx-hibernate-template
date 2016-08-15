package org.template.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = RoleEntity.TABLE_NAME, indexes = {@Index(columnList = RoleEntity.Columns.NAME)})
public class RoleEntity extends AbstractEntity {
    public static final String TABLE_NAME = "security_roles";

    public static class Columns extends AbstractEntity.Columns {
        public static final String NAME = "name";
    }

    @Column(name = Columns.NAME)
    @NotEmpty
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "security_users_roles",
            joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private List<UserEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}