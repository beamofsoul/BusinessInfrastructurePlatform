//package com.beamofsoul.springboot.management.exception;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.web.util.WebUtils;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@ControllerAdvice
//public class GlobalDefaultExceptionHandler {
//
//	@ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e)  {
////		e.printStackTrace();
//		
////		switch((Integer) request.getAttribute("javax.servlet.error.status_code")) {
////			case 403:
////				break;
////			case 404:
////				break;
////			case 500:
////				break;
////			default:
////		}
////	    WebUtils.issueRedirect(request, response, url);
////		e.printStackTrace();
//		ModelAndView mav = new ModelAndView("/error");
////      mav.addObject("exception", e);
////      mav.addObject("url", req.getRequestURL());
////      mav.setViewName(DEFAULT_ERROR_VIEW);
//		return mav;
//	}
//	
//	/**
//     * 获取错误编码
//     * @param request
//     * @return
//     */
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        try {
//            return HttpStatus.valueOf(statusCode);
//        }
//        catch (Exception ex) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//    }
//}
