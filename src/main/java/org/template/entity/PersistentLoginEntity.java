package org.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = PersistentLoginEntity.TABLE_NAME)
public class PersistentLoginEntity extends AbstractEntity {
    public static final String TABLE_NAME = "security_persistent_logins";

    public static class Columns extends AbstractEntity.Columns {
        public static final String USER_NAME = "username";
        public static final String SERIES = "series";
        public static final String TOKEN = "token";
        public static final String LAST_USED = "last_used";
    }

    @Column(name = Columns.USER_NAME)
    private String username;

    @Column(name = Columns.SERIES)
    private String series;

    @Column(name = Columns.TOKEN)
    private String token;

    @Column(name = Columns.LAST_USED)
    private Date lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}