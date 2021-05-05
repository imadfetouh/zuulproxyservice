package com.imadelfetouh.zuulproxyservice;

import com.google.gson.Gson;
import com.imadelfetouh.zuulproxyservice.filter.SignInSignUpFilter;
import com.imadelfetouh.zuulproxyservice.model.UserData;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SignInFilterTest {

//    @Test
//    public void testAttributes() {
//        SignInSignUpFilter signInFilter = new SignInSignUpFilter();
//
//        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/signin");
//        RequestContext context = new RequestContext();
//        context.setRequest(request);
//        RequestContext.testSetCurrentContext(context);
//
//        Assertions.assertEquals("post", signInFilter.filterType());
//        Assertions.assertEquals(1, signInFilter.filterOrder());
//        Assertions.assertTrue(signInFilter.shouldFilter());
//    }
//
//    @Test
//    public void testFilter() throws ZuulException {
//        SignInSignUpFilter signInFilter = new SignInSignUpFilter();
//        Gson gson = new Gson();
//        UserData userData = new UserData("1", "imad", "ADMINISTRATOR");
//        String authGson = gson.toJson(userData);
//
//        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/signin");
//        RequestContext context = new RequestContext();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        response.setStatus(200);
//        context.setResponse(response);
//        context.setResponseBody(authGson);
//        context.setRequest(request);
//        RequestContext.testSetCurrentContext(context);
//
//        signInFilter.run();
//
//        Assertions.assertEquals(authGson, context.getResponseBody());
//        Assertions.assertNotNull(context.getZuulResponseHeaders());
//
//    }
}
