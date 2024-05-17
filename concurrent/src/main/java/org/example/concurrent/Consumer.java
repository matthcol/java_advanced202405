package org.example.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Consumer {
    private int nbValues;
    private int speed;
    private int delay;
    private ConcurrentBuffer buffer;

    public void consume() {
        try {
            // Thread.sleep();
            TimeUnit.SECONDS.sleep(delay);
            for (int i = 0; i < nbValues; i++) {
                double value = buffer.pick();
                System.out.println("consume: " + value);
                TimeUnit.SECONDS.sleep(speed);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
    }
}
