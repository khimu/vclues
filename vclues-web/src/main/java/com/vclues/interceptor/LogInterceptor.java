package com.vclues.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LogInterceptor implements HandlerInterceptor {

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		log.info("Request Completed!");
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		log.info("Method executed");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		log.info("Before process request");
		/*
		 * 
		 * http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
		    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
		    private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/v[0-9]* / . * ", null);
		
		    @Override
		    public boolean matches(HttpServletRequest request) {
		        // No CSRF due to allowedMethod
		        if(allowedMethods.matcher(request.getMethod()).matches())
		            return false;
		
		        // No CSRF due to api call
		        if(apiMatcher.matches(request))
		            return false;
		
		        // CSRF for everything else that is not an API call or an allowedMethod
		        return true;
		    }
		});
		 */
		
		return true;
	}

}