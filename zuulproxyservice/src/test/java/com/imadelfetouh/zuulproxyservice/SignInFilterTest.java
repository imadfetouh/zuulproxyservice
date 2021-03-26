package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.filter.SignInFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignInFilterTest {

    @Test
    public void testAttributes() {
        SignInFilter signInFilter = new SignInFilter();

        Assertions.assertEquals("post", signInFilter.filterType());
        Assertions.assertEquals(1, signInFilter.filterOrder());
    }
}
