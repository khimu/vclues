package com.vclues.core.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.vclues.core.entity.User;

public interface IUserService {
	
	public User autoSaveFacebookLoginUsers(String email, String password);
	
	/*
	 * Used by register to find an exist email
	 */
	public User findByEmail(final String login);
	/*
	 * Search by first name, last name, or email
	 */
	public List<User> findByFirstNameOrLastName(String name);
	
	public User findById(Long userId);
	
	public List<User> findAll();
	
	public User registerNewUser(User user);
	
	public User registerNewUser(User user,String password);

	public User saveOrUpdateMerchantAccount(User user, String businessKey);
	
    public void deleteUser(User user);
    
    public void toggleUserActivation(Long userId);

	public void resetPassword(final String email);
    
    public void confirmEmail(final String email, String activationKey);
    
    public void confirmResetPassword(final String email, String resetPasswordKey, String newPassword);
	
    public String buildRecoveryPasswordUrl(final User user, final String urlTemplate);
    
    public String generateRecoveryToken(final User user);
    
    public boolean isRecoveryTokenValid(final String username, final String token);
    
    public boolean isRecoveryTokenValid(final User user, final String token);
    
    public void sendPasswordRecoveryEmail(final String username, final String urlTemplate);

    public User updatePassword(final String username, final String currentPassword, final String recoveryToken, final String newPassword, final String applicationUrl);

    public Iterable<User> findByParentUser(@Param("user") User account);
}
