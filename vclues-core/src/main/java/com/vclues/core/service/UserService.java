package com.vclues.core.service;

import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.templating.mail.service.EmailService;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.vclues.core.entity.User;
import com.vclues.core.enums.Features;
import com.vclues.core.repository.AuthorityRepository;
import com.vclues.core.repository.StoryRepository;
import com.vclues.core.repository.UserRepository;

@Service("userService")
public class UserService implements IUserService {
	
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
     
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private IPasswordTokenService passwordTokenManager;
    
    @Value("${server.base.url}")
    private String baseUrl;
    
    @Value("${spring.mail.username}")
    private String myEmail;

    //private final static String baseUrl = "http://localhost:9091/";

    private String passwordRecoveryTemplate = "vm/passwordRecovery.vm";
    private String passwordUpdatedTemplate = "vm/passwordUpdated.vm";
    
    @Autowired
    public EmailService emailService;
    
    public User findById(Long userId) {
    	return userRepository.findOne(userId);
    }
    

    @Transactional
    public User autoSaveFacebookLoginUsers(String email, String password) {

		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
		    user = this.registerNewUser(user);
		    return userRepository.save(user);
		}
		else {
			
		}
		return user;
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public User saveOrUpdateMerchantAccount(User user, String businessKey) {
    	User editUser = null;
    	if(user.getId() != null) {
    		log.info("id is " + user.getId());
    		editUser = userRepository.findOne(user.getId());
    	}
    	
        if(user.getPassword() != null && !"".equals(user.getPassword()) && (editUser == null || !user.getPassword().equals(editUser.getPassword()))) {
        	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
        }
        else {
        	user.setPassword(editUser.getPassword());
        }
        
    	// set permission to read only access
    	user.setPermissions(Features.READ_ACCOUNT.enabled(0));
    	user.setPermissions(Features.READ_DISPUTES.enabled(user.getPermissions()));
    	user.setPermissions(Features.READ_DESCRIPTOR.enabled(user.getPermissions()));
    	
    	user.getAuthorities().add(authorityRepository.findByName("ROLE_USER"));		
    	
    	return userRepository.save(user);
    }
    
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void deleteUser(User user) {
    	user.setActive(false);
    	userRepository.save(user);
    }
    
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void toggleUserActivation(Long userId) {
    	User user = userRepository.findOne(userId);
    	
    	user.setActivated(user.isActivated() ? false : true);
    	
    	userRepository.save(user);
    }
    
    @Override
    @Transactional(rollbackOn = Exception.class)
    public User registerNewUser(User user) {
    	String activationKey = RandomStringUtils.randomAlphabetic(20);

		try {
		// This needs to be asynchronous
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress(myEmail, "VClues"))
                .to(Lists.newArrayList(new InternetAddress(user.getEmail(), user.getFirstName() + " " + user.getLastName())))
                .subject("VClues Registration")
                .body("")//Empty body
                .encoding(Charset.forName("UTF-8").name()).build();
            //Defining the model object for the given Freemarker template
            final Map<String, Object> modelObject = new HashMap<>();
            modelObject.put("url", baseUrl + "/confirm/" + user.getEmail() + "/" + activationKey);

           emailService.send(email, "emails/confirm.ftl", modelObject);  
		}catch(Exception e) {
			log.error("Unable to email registration confirmation link for /confirm/" + user.getEmail() + "/" + activationKey);
			e.printStackTrace();
		}

		user.getAuthorities().add(authorityRepository.findByName("ROLE_USER"));		  
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));        
        user.setActivationKey(activationKey);
        user.setParentUser(user);

        user = userRepository.save(user);
        return user;
    }
    
    public void resetPassword(final String email) {
    	User user = userRepository.findByEmail(email);
    	
    	if(user != null) {
    		String resetPasswordKey = RandomStringUtils.randomAlphabetic(20);
    		user.setResetPasswordKey(resetPasswordKey);
    		
    		userRepository.save(user);
    		
    		// send email    		
    	}
    	
    	// otherwise do not send email
    	
    }
    
    public void confirmEmail(final String email, String activationKey) {
    	User user = userRepository.findByEmailAndActivationKey(email, activationKey);
    	
    	if(user != null) {
    		log.info("User is not null");
    		 user.setActivated(true);
             userRepository.save(user); 		
    	}
    }
    
    public void confirmResetPassword(final String email, String resetPasswordKey, String newPassword) {
    	User user = userRepository.findByEmailAndResetPasswordKey(email, resetPasswordKey);
    	
    	if(user != null) {
    		 user.setPassword(bCryptPasswordEncoder.encode(newPassword));
             userRepository.save(user); 		
    	}
    	
    	// otherwise do not send email
    }
    
    @Override
    @Transactional
    public User findByEmail(final String login) {
    	//log.info("searching for " + login);
    	
    	User user = userRepository.findByEmail(login);
        
        return user;
    }
    
    @Transactional
    public List<User> findByFirstNameOrLastName(String name) {
    	return userRepository.findByFirstNameOrLastName(name);
    }
    
    public List<User> findAll() {
    	return userRepository.findAll();
    }
    
    @Override
    public String buildRecoveryPasswordUrl(final User user, final String urlTemplate) {
        final String token = generateRecoveryToken(user);
        final String username = user.getEmail();
        return StringUtils.replaceEach(urlTemplate,
                new String[]{"{username}", "{token}"},
                new String[]{username, token});
    }

    @Override
    public String generateRecoveryToken(final User user) {
        return passwordTokenManager.generateRecoveryToken(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecoveryTokenValid(final String username, final String token) {
        return isRecoveryTokenValid(userRepository.findByEmail(username), token);
    }

    @Override
    public boolean isRecoveryTokenValid(final User user, final String token) {
        return passwordTokenManager.isRecoveryTokenValid(user, token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPasswordRecoveryEmail(final String username, final String urlTemplate) {
        log.debug("Sending password recovery token to user: " + username);

        final User user = userRepository.findByEmail(username);
        final String url = buildRecoveryPasswordUrl(user, urlTemplate);

        sendUserEmail(user, passwordRecoveryTemplate, url);
    }

    private void sendUserEmail(final User user, final String template, final String url) {
		try {
		// This needs to be asynchronous
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("imurdermystery@gmail.com", "VClues"))
                .to(Lists.newArrayList(new InternetAddress(user.getEmail(), user.getFirstName() + " " + user.getLastName())))
                .subject("Vega Clues")
                .body("")//Empty body
                .encoding(Charset.forName("UTF-8").name()).build();
            //Defining the model object for the given Freemarker template
            final Map<String, Object> modelObject = new HashMap<>();
            modelObject.put("url", baseUrl + url);
            modelObject.put("user", user);

           emailService.send(email, template, modelObject);  
		}catch(Exception e) {
			log.error("Unable to email registration confirmation link for " + url);
		}
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User updatePassword(final String username, final String currentPassword, final String recoveryToken, final String newPassword, final String applicationUrl) {
        User user = userRepository.findByEmail(username);
        if (isRecoveryTokenValid(user, recoveryToken)) {
            log.debug("Updating password from recovery token for user:" + username);
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user = userRepository.save(user);
            passwordTokenManager.invalidateRecoveryToken(user, recoveryToken);

            sendUserEmail(user, passwordUpdatedTemplate, applicationUrl);

            return user;
        } else if (StringUtils.isNotBlank(currentPassword)) {
            if (bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
                log.debug("Updating password (providing current password) for user:" + username);
                user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                user = userRepository.save(user);
                return user;
            }
        }
        // or throw exception
        return null;
    }

    public Iterable<User> findByParentUser(@Param("user") User account) {
    	return userRepository.findByParentUser(account);
    }
}
