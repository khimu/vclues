/**
 *
 */
package com.vclues.core.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vclues.core.entity.User;

/**
 * @author ivangsa
 */
@Component("passwordTokenManager")
public class PasswordTokenService implements IPasswordTokenService {
    private final Log log = LogFactory.getLog(PasswordTokenService.class);

    private final SimpleDateFormat expirationTimeFormat = new SimpleDateFormat("yyyyMMddHHmm");
    private final int expirationTimeTokenLength = expirationTimeFormat.toPattern().length();

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateRecoveryToken(final User user) {
        if (user != null) {
            final String tokenSource = getTokenSource(user);
            final String expirationTimeStamp = expirationTimeFormat.format(getExpirationTime());
            return expirationTimeStamp + bCryptPasswordEncoder.encode(expirationTimeStamp + tokenSource);
        }
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecoveryTokenValid(final User user, final String token) {
        if (user != null && token != null) {
            final String expirationTimeStamp = getTimestamp(token);
            final String tokenWithoutTimestamp = getTokenWithoutTimestamp(token);
            final String tokenSource = expirationTimeStamp + getTokenSource(user);
            final Date expirationTime = parseTimestamp(expirationTimeStamp);

            return expirationTime != null && expirationTime.after(new Date())
                    && bCryptPasswordEncoder.matches(tokenSource, tokenWithoutTimestamp);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invalidateRecoveryToken(User user, String token) {
        // NOP
    }

    /**
     * Return tokens expiration time, now + 1 day.
     *
     * @return
     */
    private Date getExpirationTime() {
        return DateUtils.addDays(new Date(), 1);
    }

    private String getTimestamp(final String token) {
        return StringUtils.substring(token, 0, expirationTimeTokenLength);
    }

    /**
     * @param user
     * @return
     */
    private String getTokenSource(final User user) {
        return user.getEmail() + user.getVersion() + user.getPassword();
    }

    private String getTokenWithoutTimestamp(final String token) {
        return StringUtils.substring(token, expirationTimeTokenLength, token.length());
    }

    private Date parseTimestamp(final String timestamp) {
        try {
            return expirationTimeFormat.parse(timestamp);
        } catch (final ParseException e) {
            return null;
        }
    }

}
