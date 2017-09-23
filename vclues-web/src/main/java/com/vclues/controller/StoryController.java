package com.vclues.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.mongo.repository.AnnouncementRepository;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;
import com.vclues.core.validator.DescriptorValidator;

@Controller
//@RequestMapping(value = "/")
public class StoryController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(StoryController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;

	@Autowired
	private AnnouncementRepository descriptorRepository;
	
	@Autowired
	private DescriptorValidator descriptorValidator;

    @GetMapping("/videos")
    public String list(Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		
		
		List<Story> stories = storyService.getAllStories();
		
		model.addAttribute("stories", stories);
		
		// PAID == 2
		model.addAttribute("isPaid", user.getType());
		model.addAttribute("content", "listStory"); 
		model.addAttribute("title", "Stories");
		
        return "videos";
    }

}
