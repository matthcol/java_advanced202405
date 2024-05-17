package org.example.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Productor {
    private int nbValues;
    private int speed;
    private int delay;
    private ConcurrentBuffer buffer;

    public void product() {
        try {
            TimeUnit.SECONDS.sleep(delay);
            for (int i = 0; i < nbValues; i++) {
                double value = i; // random, ...
                System.out.println("product: " + value);
                buffer.depose(value);
                TimeUnit.SECONDS.sleep(speed);
            }
        } catch (InterruptedException e) {
            System.out.println("Productor interrupted");
        }
    }
}
