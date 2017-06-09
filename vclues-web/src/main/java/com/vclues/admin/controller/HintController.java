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

import com.vclues.core.entity.Cast;
import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Scene;
import com.vclues.core.entity.User;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller("adminHintController")
@RequestMapping(value = "/admin/hint")
public class HintController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(HintController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;

    @GetMapping("/all/{id}")
    public String list(@PathVariable("id") String sceneId, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		List<Hint> hints = storyService.getAllHintBySceneId(Long.parseLong(sceneId));
		
		model.addAttribute("hints", hints);
		
		model.addAttribute("content", "listHint"); 
		model.addAttribute("title", "All Hints");
		
        return "admin";
    }

    /*
    @GetMapping("{id}")
    public String view(@PathVariable("id") String hintId, Model model) {
    	logger.info("View hint " + hintId);
    	
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(hintId == null || !StringUtils.isNumeric(hintId)) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/welcome";
		}
		
		Hint hint = storyService.getHint(Long.parseLong(hintId));
		
		model.addAttribute("hint", hint);
		
		model.addAttribute("content", "hintDetail"); 
		model.addAttribute("title", "Hint Detail");
        
		return "internal";
    }
    */

    @GetMapping("/edit/{id}/{storyId}")
    public String edit(@PathVariable("id") String hintId, @PathVariable("storyId") String storyId, Model model) {
    	logger.info("Edit hint " + hintId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Hint hint = storyService.getHint(Long.parseLong(hintId));

		model.addAttribute("hint", hint);
		model.addAttribute("storyId", storyId);
		model.addAttribute("content", "editHint"); 
		model.addAttribute("title", "Edit Hint Detail");
        
		return "admin";
    }
	    
    @GetMapping("{id}")
    public String add(@PathVariable("id") String storyId, Model model) {
    	logger.info("In add hint");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Hint hint = new Hint();

		model.addAttribute("hint", hint);
		model.addAttribute("scenes", storyService.getAllSceneByStoryId(Long.parseLong(storyId)));
		model.addAttribute("storyId", storyId);
        model.addAttribute("content", "addHint");
        model.addAttribute("title", "Add Hint");
        
        return "admin";
    }

    /*
     * Edit descriptor detail
     */
    @PutMapping
    public String put(@ModelAttribute("hint") Hint hint, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		// need to make sure user has access to the descriptor
		storyService.saveHint(hint);
		
        return "redirect:/admin/hint/all";
    }


    /*
     * Ajax call
     * Edit descriptor detail
     */
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void delete(@RequestHeader("id") String hintId) {
    	logger.info("In delete hint " + hintId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(hintId == null || !StringUtils.isNumeric(hintId)) {
			logger.info("hintId is null");
			return;
		}
		
		storyService.deleteHint(Long.parseLong(hintId));
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
    public String post(@RequestParam("storyId") String storyId, @ModelAttribute("hint") Hint hint, BindingResult bindingResult, Model model) {
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
		
		storyService.saveHint(hint);
        
        return "redirect:/admin/story/" + storyId;
    }    


}
