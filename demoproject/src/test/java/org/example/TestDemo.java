package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDemo {

    @Test
    void testJunit_Ok(){
        var text = "Demo";
        assertEquals("Demo", text);
    }
}