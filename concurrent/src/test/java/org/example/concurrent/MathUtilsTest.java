package org.example.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void testPiMonteCarlo() {
        double pi = MathUtils.piMonteCarlo();
        assertEquals(Math.PI, pi, 1E-15);
    }
}