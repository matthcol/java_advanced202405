package org.example.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PairDouble {
    private double first;
    private double second;
}
