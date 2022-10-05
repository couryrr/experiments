package com.dappercloud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * WeirdNumberTest
 */
public class WeirdNumberTest {

    @Test
    public void testCheckUseCase1() {
        String response = WeirdNumber.check(3);
        assertEquals("Weird", response);
    }

    @Test
    public void testCheckUseCase2() {
        String response = WeirdNumber.check(24);
        assertEquals("Not Weird", response);
    }
}