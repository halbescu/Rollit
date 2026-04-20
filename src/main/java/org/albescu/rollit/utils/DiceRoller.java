package org.albescu.rollit.utils;

import org.albescu.rollit.classes.RollResult;

import java.util.Random;

public class DiceRoller {
    public static Random rand = new Random();

    public static RollResult Roll(int dice, int sides) {
        int[] results = new int[dice];
        for (int i = 0; i < dice; i++) {
            results[i] = rand.nextInt(sides) + 1;
        }

        return new RollResult(results);
    }
}
