//package com.security.web.config;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//public class SessionTimeoutCookieFilter implements Filter {
//
//	private static final Log log = LogFactory.getLog(SessionTimeoutCookieFilter.class);
//
//    @Override
//    public void init(FilterConfig config) throws ServletException {
//        log.info("Initialization SessionTimeoutCookieFilter");
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpResp = (HttpServletResponse) resp;
//        HttpServletRequest httpReq = (HttpServletRequest) req;
//
//        long currTime = System.currentTimeMillis();
//        String expiryTime = Long.toString(currTime + httpReq.getSession().getMaxInactiveInterval() * 1000);
//        Cookie cookie = new Cookie("serverTime", Long.toString(currTime));
//        cookie.setPath("/");
//        httpResp.addCookie(cookie);
//        if (httpReq.getRemoteUser() != null) {
//            cookie = new Cookie("sessionExpiry", expiryTime);
//        }
//        cookie.setPath("/");
//        httpResp.addCookie(cookie);
//
//        filterChain.doFilter(req, resp);
//    }
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//}
