package org.template.service;

import org.template.dao.PersistentLoginRepository;
import org.template.entity.PersistentLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersistentLoginService extends AbstractService implements PersistentTokenRepository {
    @Autowired
    private PersistentLoginRepository persistentLoginRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLoginEntity userPersistentToken = new PersistentLoginEntity();
        userPersistentToken.setSeries(token.getSeries());
        userPersistentToken.setUsername(token.getUsername());
        userPersistentToken.setToken(token.getTokenValue());
        userPersistentToken.setLastUsed(token.getDate());
        persistentLoginRepository.save(userPersistentToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLoginEntity userPersistentToken = persistentLoginRepository.findBySeries(series);
        if (userPersistentToken != null) {
            userPersistentToken.setToken(tokenValue);
            userPersistentToken.setLastUsed(lastUsed);
            persistentLoginRepository.save(userPersistentToken);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLoginEntity userPersistentToken = persistentLoginRepository.findBySeries(seriesId);
        if (userPersistentToken == null) {
            return null;
        }
        return new PersistentRememberMeToken(
                userPersistentToken.getUsername(),
                userPersistentToken.getSeries(),
                userPersistentToken.getToken(),
                userPersistentToken.getLastUsed());
    }

    @Override
    public void removeUserTokens(String username) {}

    public void removeUserTokenBySeries(String seriesId) {
        persistentLoginRepository.deleteBySeries(seriesId);
    }
}