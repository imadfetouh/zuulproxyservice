package com.imadelfetouh.zuulproxyservice;

import com.google.gson.Gson;
import com.imadelfetouh.zuulproxyservice.filter.JWTFilter;
import com.imadelfetouh.zuulproxyservice.filter.SignInFilter;
import com.imadelfetouh.zuulproxyservice.model.AuthModel;
import com.imadelfetouh.zuulproxyservice.producer.CreateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;

public class JWTFilterTest {

    @Test
    public void testAttributes() {
        JWTFilter jwtFilter = new JWTFilter();

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/test");
        RequestContext context = new RequestContext();
        context.setRequest(request);
        RequestContext.testSetCurrentContext(context);

        Assertions.assertEquals("pre", jwtFilter.filterType());
        Assertions.assertEquals(1, jwtFilter.filterOrder());
        Assertions.assertTrue(jwtFilter.shouldFilter());
    }

    @Test
    public void testFilter() throws ZuulException {
        //get token first
        RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
        CreateTokenProducer createTokenProducer = new CreateTokenProducer("1");
        String token = rabbitProducer.produce(createTokenProducer);

        JWTFilter jwtFilter = new JWTFilter();

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/test");
        MockHttpServletResponse response = new MockHttpServletResponse();
        RequestContext context = new RequestContext();

        Cookie cookie = new Cookie("jwt-token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        Cookie[] cookies = new Cookie[1];
        cookies[0] = cookie;
        request.setCookies(cookies);

        context.setRequest(request);
        context.setResponse(response);

        RequestContext.testSetCurrentContext(context);

        jwtFilter.run();

        //Assertions.assertEquals(authGson, context.getResponseBody());
        //Assertions.assertNotNull(context.getZuulResponseHeaders());

    }
}
