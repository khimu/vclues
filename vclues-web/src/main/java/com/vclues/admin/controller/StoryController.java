package com.vclues.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.entity.Authority;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.mongo.repository.AnnouncementRepository;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;
import com.vclues.core.validator.DescriptorValidator;

@Controller("adminStoryController")
@RequestMapping(value = "/admin/story")
public class StoryController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(StoryController.class);

	@Value("${web.url}")
	protected String webUrl;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;

	@Autowired
	private AnnouncementRepository descriptorRepository;
	
	@Autowired
	private DescriptorValidator descriptorValidator;

    @GetMapping("/all")
    public String list(Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		List<Story> stories = new ArrayList<Story>();

		for(Authority auth : user.getAuthorities()) {
			if(auth.getName().equals("ROLE_ADMIN")) {
				stories = storyService.getAllStories();
				break;
			}
		}

		if(stories.isEmpty()) {
			stories = storyService.findAllStoryByUser(user);
		}
		
		model.addAttribute("stories", stories);		
		//model.addAttribute("content", "listStory"); 
		model.addAttribute("content", "welcome");
		model.addAttribute("title", "Stories");
		model.addAttribute("userId", user.getId());
		
		
		
		model.addAttribute("webUrl", webUrl);
		
		model.addAttribute("baseUrl", baseUrl);
		
		return "admin";
    }

    @GetMapping("{id}")
    public String view(@PathVariable("id") String storyId, Model model) {
    	logger.info("View story " + storyId);
    	
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(storyId == null || !StringUtils.isNumeric(storyId)) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/admin/welcome";
		}
		
		Story story = storyService.getStory(Long.parseLong(storyId));
		
		model.addAttribute("story", story);

		//model.addAttribute("casts", storyService.getAllCastByStoryId(Long.parseLong(storyId)));	
		//model.addAttribute("scenes", storyService.getAllSceneByStoryId(Long.parseLong(storyId)));	
		model.addAttribute("content", "storyDetail"); 
		model.addAttribute("title", "Story Detail");
		model.addAttribute("userId", user.getId());
		
		model.addAttribute("webUrl", webUrl);
        
		return "admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String storyId, Model model) {
    	logger.info("Edit story " + storyId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Story story = storyService.getStory(Long.parseLong(storyId));

		model.addAttribute("story", story);
		
		model.addAttribute("content", "editStory"); 
		model.addAttribute("title", "Edit Story Detail");
		model.addAttribute("userId", user.getId());
		
		model.addAttribute("webUrl", webUrl);
        
		return "admin";
    }
	    
    @GetMapping
    public String add(Model model) {
    	logger.info("In add story");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Story story = new Story();
		story.setUserId(user.getId());
		model.addAttribute("story", story);
		
        model.addAttribute("content", "addStory");
        model.addAttribute("title", "Add Story");
        model.addAttribute("userId", user.getId());
        
        model.addAttribute("webUrl", webUrl);
        
        return "admin";
    }

    /*
     * Edit descriptor detail
     */
    /*
    @PutMapping
    public String put(@ModelAttribute("story") Story story, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		logger.info("In edit of story " + story.getId());

		Story s = storyService.getStory(story.getId());
		
		if(s.getUser().getId().equals(story.getUser().getId())) {
			// need to make sure user has access to the descriptor
			storyService.saveStory(story);
		}
		else {
			logger.warn("User " + user.getEmail() + " trying to update story " + story.getId()  + " " + story.getTitle());
		}

        return "redirect:/admin/story/all";
    }
    */

    /*
     * Ajax call
     * Edit descriptor detail
     */
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void ajaxDelete(@RequestHeader("id") String storyId) {
    	logger.info("In delete story " + storyId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(storyId == null || !StringUtils.isNumeric(storyId)) {
			logger.info("storyId is null");
			return;
		}
		
		Story s = storyService.getStory(Long.parseLong(storyId));
		
		if(user.getId().equals(s.getUserId())) {
			storyService.deleteStory(Long.parseLong(storyId));
		}
		
		else {
			logger.warn("User " + user.getEmail() + " trying to delete " + s.getTitle() );
		}
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String storyId) {
    	logger.info("In delete story " + storyId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		if(storyId == null || !StringUtils.isNumeric(storyId)) {
			logger.info("storyId is null");
			 return "redirect:/admin/story/all";
		}
		
		Story s = storyService.getStory(Long.parseLong(storyId));
		
		if(user.getId().equals(s.getUserId())) {
			storyService.deleteStory(Long.parseLong(storyId));
		}
		
		else {
			logger.warn("User " + user.getEmail() + " trying to delete " + s.getTitle() );
		}
		
		 return "redirect:/admin/story/all";
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
    public String post(@ModelAttribute("story") Story story, BindingResult bindingResult, Model model) {
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
		
		logger.info("In edit of story " + story.getId());

		Story s = storyService.getStory(story.getId());
		
		if(s == null) {
			story.setUserId(user.getId());
	        storyService.saveStory(story);
		}else {
			if(s.getUserId().equals(story.getUserId())) {
				// need to make sure user has access to the descriptor
				storyService.saveStory(story);
			}
			else {
				logger.warn("User " + user.getEmail() + " trying to update story " + story.getId()  + " " + story.getTitle());
				story.setUserId(user.getId());
		        storyService.saveStory(story);
			}
		}
		

         
        return "redirect:/admin/story/all";
    }    

}
