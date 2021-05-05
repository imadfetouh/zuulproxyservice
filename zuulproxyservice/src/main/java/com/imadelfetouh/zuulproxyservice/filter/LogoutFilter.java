package com.imadelfetouh.zuulproxyservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class LogoutFilter extends ZuulFilter {

    private String logoutURI;
    private String cookieName;

    public LogoutFilter() {
        logoutURI = "/logout";
        cookieName = "jwt-token";
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext context = RequestContext.getCurrentContext();
        final String requestURI = context.getRequest().getRequestURI();

        return requestURI.equals(logoutURI);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulResponseHeader("Set-Cookie", "jwt-token=null; Path=/; MaxAge=0; HttpOnly;");

        requestContext.setResponseStatusCode(200);

        return null;
    }
}
