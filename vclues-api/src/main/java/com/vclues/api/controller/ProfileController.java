package com.vclues.api.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.profile.internal.Profile;
import com.google.gson.Gson;
import com.vclues.core.entity.User;

/**
 * 
 * @author khimung
 *
 */
@RestController
public class ProfileController {
	
	private final static Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	/*
	 * According to stackoverflow this is thread safe
	 */
	private static final Gson gson = new Gson();
	
	@Resource
	private RabbitTemplate amqpTemplate;
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	/**
	 * {@link /profile}
	 * 
	 * @param profile
	 */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String create(@RequestBody User user) {
    	final String retrievalKey = RandomStringUtils.randomAlphabetic(20)+"@gmail.com";
		String hashedPassword = passwordEncoder.encode(RandomStringUtils.randomAlphanumeric(5));
		    	
    	return retrievalKey;
    }
    
    
    /*
     * username: guest
     * password: guest
     * 
     * http://107.170.234.144:15672/
     * 
     * tail -f /var/log/rabbitmq/rabbit@ubuntu-1gb-sfo1-01.log
     * 
     * By default, you should enable 5672 (rabbit mq port) and 4365 (empd port)
     */        
    //@RabbitListener(queues = "profiles", containerFactory="connectionFactory")
    public void listen(Profile profile) {
    	logger.info("Calling listen");    	
	}
}
