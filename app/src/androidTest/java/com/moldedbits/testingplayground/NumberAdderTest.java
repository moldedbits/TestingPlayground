package com.moldedbits.testingplayground;

import com.moldedbits.testingplayground.NumberAdder;

import junit.framework.TestCase;

public class NumberAdderTest extends TestCase {

    public void testAdder() throws Exception {
        int result = NumberAdder.addNumbers(2, 3);
        assertEquals(5, result);
    }
}
