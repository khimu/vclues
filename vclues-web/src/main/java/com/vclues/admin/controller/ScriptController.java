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

import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Scene;
import com.vclues.core.entity.Script;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller("adminScriptController")
@RequestMapping(value = "/admin/script")
public class ScriptController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(ScriptController.class);

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
		
		List<Script> scripts = storyService.getAllScriptsBySceneId(Long.parseLong(sceneId));
		
		model.addAttribute("scripts", scripts);
		
		model.addAttribute("content", "listScript"); 
		model.addAttribute("title", "All Scripts");
		
        return "admin";
    }

    /*
    @GetMapping("{id}")
    public String view(@PathVariable("id") String scriptId, Model model) {
    	logger.info("View script " + scriptId);
    	
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(scriptId == null || !StringUtils.isNumeric(scriptId)) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/welcome";
		}
		
		Script script = storyService.getScript(Long.parseLong(scriptId));
		
		model.addAttribute("script", script);
		
		model.addAttribute("content", "scriptDetail"); 
		model.addAttribute("title", "Script Detail");
        
		return "internal";
    }
	*/

    @GetMapping("/edit/{id}/{storyId}")
    public String edit(@PathVariable("id") String scriptId, @PathVariable("storyId") String storyId, Model model) {
    	logger.info("Edit script " + scriptId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Script script = storyService.getScript(Long.parseLong(scriptId));

		model.addAttribute("script", script);
		model.addAttribute("storyId", storyId);
		model.addAttribute("content", "editScript"); 
		model.addAttribute("title", "Edit Script Detail");
        
		return "admin";
    }
	    
    @GetMapping("{id}")
    public String add(@PathVariable("id") String storyId, Model model) {
    	logger.info("In add script");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Script script = new Script();

		model.addAttribute("casts", storyService.getAllCastByStoryId(Long.parseLong(storyId)));	
		model.addAttribute("scenes", storyService.getAllSceneByStoryId(Long.parseLong(storyId)));	
		model.addAttribute("storyId", storyId);
		model.addAttribute("script", script);
        model.addAttribute("content", "addScript");
        model.addAttribute("title", "Add Script");
        
        return "admin";
    }

    /*
     * Edit descriptor detail
     */
    @PutMapping
    public String put(@ModelAttribute("script") Script script, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		// need to make sure user has access to the descriptor
		storyService.saveScript(script);
		
        return "redirect:/admin/script/all";
    }


    /*
     * Ajax call
     * Edit descriptor detail
     */
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void delete(@RequestHeader("id") String scriptId) {
    	logger.info("In delete script " + scriptId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(scriptId == null || !StringUtils.isNumeric(scriptId)) {
			logger.info("scriptId is null");
			return;
		}
		
		storyService.deleteScript(Long.parseLong(scriptId));
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
    public String post(@RequestParam("storyId") String storyId, @ModelAttribute("script") Script script, BindingResult bindingResult, Model model) {
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
        
		storyService.saveScript(script);
		
        return "redirect:/admin/story/" + storyId;
        
    }    


}
