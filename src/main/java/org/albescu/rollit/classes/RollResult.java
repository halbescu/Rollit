package org.albescu.rollit.classes;

import java.util.ArrayList;
import java.util.List;

public class RollResult {
    private int[] results = new int[0];

    public RollResult(int[] results) {
        this.results = results;
    }

    public int getSum() {
        int sum = 0;
        for (int result : results) {
            sum += result;
        }

        return sum;
    }
}
