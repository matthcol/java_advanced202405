package org.example.concurrent;

import java.text.MessageFormat;
import java.util.Arrays;

public class ConcurrentBuffer {
    private double[] buffer;
    private int queue;
    private int head;
    private boolean full;
    private boolean empty;

    public ConcurrentBuffer(int size){
        if (size <=0) throw new IllegalArgumentException("Buffer size must be >0");
        buffer = new double[size];
        for (int i=0; i<size; i++){
            buffer[i] = Double.NaN;
        }
        queue = 0;
        head = 0;
        empty = true;
        full = false;
    }

    public synchronized void depose(double value) throws InterruptedException {
        if (full) wait();
        buffer[queue] = value;
        empty = false;
        queue = (queue + 1) % buffer.length;
        if (queue == head) full = true;
        notify();
    }

    public synchronized double pick() throws InterruptedException {
        if (empty) wait();
        double res = buffer[head];
        buffer[head] = Double.NaN;
        full = false;
        head = (head + 1) % buffer.length;
        if (queue == head) empty = true;
        notify();
        return res;
    }
    @Override
    public String toString() {
        return MessageFormat.format("Buffer[size={0}, q={1}, h={2}, f={3}, e={4}, data={5}]",
                buffer.length,
                queue,
                head,
                full,
                empty,
                Arrays.toString(buffer)
        );
    }
}
