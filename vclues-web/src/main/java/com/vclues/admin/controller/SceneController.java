package com.vclues.admin.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.vclues.core.entity.Scene;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller("adminSceneController")
@RequestMapping(value = "/admin/scene")
public class SceneController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(SceneController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;

	/*
    @GetMapping("/all/{id}")
    public String list(@PathVariable("id") String storyId, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		List<Scene> scenes = storyService.getAllSceneByStoryId(Long.parseLong(storyId));
		
		model.addAttribute("scenes", scenes);
		
		model.addAttribute("content", "listScene"); 
		model.addAttribute("title", "All Stories");
		
        return "internal";
    }
    */

    @GetMapping("/{id}/{storyId}")
    public String view(@PathVariable("id") String sceneId, @PathVariable("storyId") String storyId, Model model) {
    	logger.info("View scene " + sceneId);
    	
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(sceneId == null || !StringUtils.isNumeric(sceneId)) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/admin/welcome";
		}
		
		Scene scene = storyService.getScene(Long.parseLong(sceneId));
		
		model.addAttribute("scene", scene);
		model.addAttribute("storyId", storyId);
		model.addAttribute("content", "sceneDetail"); 
		model.addAttribute("title", "Scene Detail");
        
		return "admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String sceneId, Model model) {
    	logger.info("Edit scene " + sceneId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Scene scene = storyService.getScene(Long.parseLong(sceneId));

		model.addAttribute("scene", scene);
		
		model.addAttribute("content", "editScene"); 
		model.addAttribute("title", "Edit Scene Detail");
        
		return "admin";
    }
	    
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String storyId, Model model) {
    	logger.info("In add scene");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		//Story story = storyService.getStory(Long.parseLong(storyId));
		
		Scene scene = new Scene();
		Story story = new Story();
		story.setId(Long.parseLong(storyId));
		scene.setStory(story);
		
		model.addAttribute("scene", scene);
		model.addAttribute("storyId", storyId);
        model.addAttribute("content", "addScene");
        model.addAttribute("title", "Add Scene");
        
        return "admin";
    }

    /*
     * Edit descriptor detail
     */
    @PutMapping
    public String put(@ModelAttribute("scene") Scene scene, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		// need to make sure user has access to the descriptor
		storyService.saveScene(scene);
		
        return "redirect:/admin/scene/all";
    }


    /*
     * Ajax call
     * Edit descriptor detail
     */
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void delete(@RequestHeader("id") String sceneId) {
    	logger.info("In delete scene " + sceneId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(sceneId == null || !StringUtils.isNumeric(sceneId)) {
			logger.info("sceneId is null");
			return;
		}
		
		storyService.deleteScene(Long.parseLong(sceneId));
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
    public String post(@ModelAttribute("scene") Scene scene, BindingResult bindingResult, Model model) {
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
        
        return "redirect:/admin/story/" + storyService.saveScene(scene).getStory().getId();
    }    


}
