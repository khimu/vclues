package com.vclues.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Basic Controller which is called for unhandled errors
 */
@ControllerAdvice
public class AppErrorController {

	private static Logger logger = LoggerFactory.getLogger(AppErrorController.class);

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Throwable throwable, final Model model) {
		logger.error("Exception during execution of SpringSecurity application", throwable);
		String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
		
		logger.error(errorMessage);
		model.addAttribute("message", "There is a slight technicality.  Please try again.");
		
		return "error";
	}

	/*
	 *     /*
     *     @RequestMapping(value = { URL.Post.HOME, URL.Post.LIST },
            method = RequestMethod.GET)
    public String list(Model model, Pageable pageRequest) {
        final PageWrapper<Post> postList =
                new PageWrapper<Post>(postService.getPost(pageRequest),
                        URL.Post.LIST);
        if (postList.getSize() == 0) {
            LOG.info("No post found redirect to create");
            return URL.redirect(URL.Post.CREATE);
        }
        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("postList", postList);
        return "example/post/list";
    }
     */
}
