package com.imadelfetouh.zuulproxyservice.filter;

import com.google.common.io.CharStreams;
import com.imadelfetouh.zuulproxyservice.producer.CreateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SignInFilter extends ZuulFilter {
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
        RequestContext requestContext = RequestContext.getCurrentContext();
        return requestContext.get("proxy") != null && requestContext.get("proxy").equals("signin");
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
                Integer userId = Integer.parseInt(response);

                RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
                CreateTokenProducer createTokenProducer = new CreateTokenProducer(userId);
                String token = rabbitProducer.produce(createTokenProducer);

                requestContext.addZuulResponseHeader("Set-Cookie", "jwt-token="+token+"; Domain:localhost; Path=/; HttpOnly");
                requestContext.setResponseBody(token);
            }
        }
        catch (Exception e){
            requestContext.setResponseStatusCode(500);
        }

        return null;
    }
}
