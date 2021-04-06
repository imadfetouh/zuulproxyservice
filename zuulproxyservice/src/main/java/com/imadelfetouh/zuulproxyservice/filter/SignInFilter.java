package com.imadelfetouh.zuulproxyservice.filter;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.imadelfetouh.zuulproxyservice.model.AuthModel;
import com.imadelfetouh.zuulproxyservice.producer.CreateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
                Gson gson = new Gson();
                String response = CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                AuthModel authModel = gson.fromJson(response, AuthModel.class);
                String userId = authModel.getUserId();

                RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
                CreateTokenProducer createTokenProducer = new CreateTokenProducer(userId);
                String token = rabbitProducer.produce(createTokenProducer);

                requestContext.addZuulResponseHeader("Set-Cookie", "jwt-token="+token+"; Path=/; HttpOnly; Same-Site=Strict");
                requestContext.setResponseBody(gson.toJson(authModel));
            }
        }
        catch (Exception e){
            requestContext.setResponseStatusCode(500);
        }

        return null;
    }
}
