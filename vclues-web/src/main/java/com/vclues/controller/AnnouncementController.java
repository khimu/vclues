package com.vclues.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.data.Announcement;
import com.vclues.core.entity.User;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IUserService;

@Controller
@RequestMapping(value = "/announcement")
public class AnnouncementController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(AnnouncementController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGameService gameService;

    @GetMapping
    public String add(Model model) {
    	logger.info("In add Announcement");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

        model.addAttribute("content", "addAnnouncement");
        model.addAttribute("title", "Add Announcement");
        
        return "internal";
    }

    /*
     * Ajax call
     * Edit descriptor detail
     */
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void delete(@RequestHeader("id") String announcementId) {
    	logger.info("In delete announcementId " + announcementId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(announcementId == null || !StringUtils.isNumeric(announcementId)) {
			logger.info("castId is null");
			return;
		}
		
		gameService.deleteAnnouncement(Long.parseLong(announcementId));
    }
	    
    /**
     * Merchant registration 
     * 
     * @param descriptor
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping
    public String post(@ModelAttribute("announcementId") Announcement announcement, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

    	// descriptorValidator.validate(descriptor, bindingResult);
    	
        //if (bindingResult.hasErrors()) {
        	//logger.info("Binding error.  Saved descriptor " + descriptor.toString());
        	//for(ObjectError error: bindingResult.getAllErrors()) {
        	//	logger.info("code " + error.getCode() + " " + error.getDefaultMessage());
        	//}
        	//model.addAttribute("descriptor", descriptor);
        	//model.addAttribute("content", "addDescriptor");
        	//model.addAttribute("title", "Add Descriptor");
            //return "internal";
        //}
                       
        //logger.info("Saving descriptor " + descriptor.toString());
        
        gameService.saveAnnouncement(announcement);
         
        return "redirect:/cast/all";
    }    

}
