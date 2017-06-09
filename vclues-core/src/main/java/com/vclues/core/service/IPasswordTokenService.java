package com.vclues.core.service;

import com.vclues.core.entity.User;


public interface IPasswordTokenService {

    /**
     * {@inheritDoc}
     */
    String generateRecoveryToken(User user);

    /**
     * {@inheritDoc}
     */
    boolean isRecoveryTokenValid(User user, String token);

    void invalidateRecoveryToken(User user, String token);
}