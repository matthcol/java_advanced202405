package org.example.concurrent;

import java.util.Random;
import java.util.stream.LongStream;

public class MathUtils {

    private static final long DEFAULT_MAX_ITERATION = 100_000_000L;
    public static double piMonteCarlo(){
        long nbPoints = DEFAULT_MAX_ITERATION;

        Random rd = new Random();
        long count = LongStream.range(0, nbPoints)
                .mapToDouble(i -> {
                    double x = rd.nextDouble();
                    double y = rd.nextDouble();
                    return x * x + y * y;
                })
                .filter(d2 -> d2 < 1)
                .count();
        return 4.0 * count / nbPoints;
    }

}
