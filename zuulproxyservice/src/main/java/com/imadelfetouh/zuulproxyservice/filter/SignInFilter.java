package com.imadelfetouh.zuulproxyservice.filter;

import com.google.common.io.CharStreams;
import com.imadelfetouh.zuulproxyservice.jwt.CreateJWTToken;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SignInFilter extends ZuulFilter {

    private final String uri;

    public SignInFilter() {
        uri = "/signin";
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext context = RequestContext.getCurrentContext();
        final String requestURI = context.getRequest().getRequestURI();
        return requestURI.equals(uri);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();

        if(requestContext.getResponse().getStatus() != 200){
            return null;
        }

        try(InputStream inputStream  = requestContext.getResponseDataStream()){
            if(inputStream != null){
                String response = CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                Map<String, String> claims = new HashMap<>();
                claims.put("userdata", response);
                String token = CreateJWTToken.getInstance().create(claims);

                requestContext.addZuulResponseHeader("Set-Cookie", "jwt-token="+token+"; Path=/; HttpOnly; Same-Site=Strict");
            }
        }
        catch (Exception e){
            requestContext.setResponseStatusCode(500);
        }

        return null;
    }
}
