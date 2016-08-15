package org.template.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable {
    public static class Columns {
        public static final String ID = "id";
    }

    /**
     * Generator UUID IETF RFC 4122
     */
    @Id
    @GeneratedValue(generator = "generator-uuid")
    @GenericGenerator(name = "generator-uuid", strategy = "uuid2")
    @Column(name = Columns.ID)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public AbstractEntity clone() throws CloneNotSupportedException {
        return (AbstractEntity) super.clone();
    }
}