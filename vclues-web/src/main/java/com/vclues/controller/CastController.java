package com.vclues.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vclues.core.entity.Cast;
import com.vclues.core.entity.User;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;


@Controller
@RequestMapping(value = "/cast")
public class CastController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(CastController.class);
	
	private final static String LEFT = "0";
	private final static String RIGHT = "1";
	private final static String TOP = "2";
	private final static String BOTTOM = "3";
	
	private static Map<String, String> VIEW = new HashMap<String, String>();
	
	static {
		VIEW.put(LEFT, "scrollinleft");
		VIEW.put(RIGHT, "scrollinright");
		VIEW.put(TOP, "scrollintop");
		VIEW.put(BOTTOM, "scrollinbottom");
	}
	

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;

	
    @GetMapping("/all/{id}")
    public String list(@PathVariable("id") String storyId, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		List<Cast> casts = storyService.getAllCastByStoryId(Long.parseLong(storyId));
		
		model.addAttribute("casts", casts);
		
		//model.addAttribute("content", "listCasts"); 
		model.addAttribute("title", "All Casts");
		
        return "allcharacters";
    }

    
    @GetMapping("/detail/{id}")
    public String view(@PathVariable("id") String castId, @RequestParam("index") String idx, Model model) {
    	logger.info("View cast " + castId);
    	
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(castId == null || !StringUtils.isNumeric(castId)) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/welcome";
		}
		
		Cast cast = storyService.getCast(Long.parseLong(castId));
		
		model.addAttribute("cast", cast);
		
		//model.addAttribute("content", "castDetail"); 
		model.addAttribute("title", "Cast Detail");
		model.addAttribute("SUSPECT-001", "suspect");
        
		
		String view = VIEW.get(idx);
		
		return view;
    }
    

	/*  
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String castId, Model model) {
    	logger.info("Edit cast " + castId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Cast cast = storyService.getCast(Long.parseLong(castId));
		
		model.addAttribute("cast", cast);
		
		model.addAttribute("content", "editCast"); 
		model.addAttribute("title", "Edit Cast");
        
		return "internal";
    }
     
    @GetMapping("{id}")
    public String add(@PathVariable("id") String storyId, Model model) {
    	logger.info("In add cast");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Story story = storyService.getStory(Long.parseLong(storyId));
		Cast cast = new Cast();
		cast.setStory(story);
		
		model.addAttribute("cast", cast);
		model.addAttribute("storyId", storyId);
        model.addAttribute("content", "addCast");
        model.addAttribute("title", "Add Cast");
        
        return "internal";
    }

    *
     * Edit descriptor detail
     *
    @PutMapping
    public String put(@ModelAttribute("cast") Cast cast, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		// need to make sure user has access to the descriptor
		storyService.saveCast(cast);
		
        return "redirect:/cast/all";
    }


    *
     * Ajax call
     * Edit descriptor detail
     *
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void delete(@RequestHeader("id") String castId) {
    	logger.info("In delete story " + castId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(castId == null || !StringUtils.isNumeric(castId)) {
			logger.info("castId is null");
			return;
		}
		
		storyService.deleteCast(Long.parseLong(castId));
    }
	    
    **
     * Merchant registration 
     * 
     * @param descriptor
     * @param bindingResult
     * @param model
     * @return
     *
    @PostMapping
    public String post(@ModelAttribute("cast") Cast cast, BindingResult bindingResult, Model model) {
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

        return "redirect:/story/" + storyService.saveCast(cast).getStory().getId();
    }  
    */  

}
