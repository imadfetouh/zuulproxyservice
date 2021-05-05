package com.imadelfetouh.zuulproxyservice;

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
