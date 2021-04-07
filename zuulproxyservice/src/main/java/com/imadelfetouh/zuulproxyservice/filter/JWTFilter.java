package com.imadelfetouh.zuulproxyservice.filter;

import com.imadelfetouh.zuulproxyservice.producer.ValidateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.Cookie;
import java.util.Arrays;

public class JWTFilter extends ZuulFilter {

    private final String signinURI;
    private final String cookieName;

    public JWTFilter() {
        signinURI = "/signin";
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
        return !requestURI.equals(signinURI);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Cookie[] cookies = requestContext.getRequest().getCookies();
        if(cookies == null || cookies.length == 0) {
            requestContext.setResponseStatusCode(401);
            requestContext.setSendZuulResponse(false);
            return null;
        }

        Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(cookieName)).findFirst().orElse(null);

        if(cookie != null) {
            RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
            ValidateTokenProducer validateTokenProducer = new ValidateTokenProducer(cookie.getValue());
            String token = rabbitProducer.produce(validateTokenProducer);

            if(token != null) {
                requestContext.addZuulRequestHeader("Set-Cookie", "jwt-token="+token+"; Path=/; HttpOnly; Same-Site=Strict");
            }
            else{
                requestContext.setResponseStatusCode(401);
                requestContext.setSendZuulResponse(false);
            }
        }
        else{
            requestContext.setResponseStatusCode(401);
            requestContext.setSendZuulResponse(false);
        }

        return null;
    }
}
