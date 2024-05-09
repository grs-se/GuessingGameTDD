package com.javafoundations.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuessingGameTest {
    private GuessingGame game;
    // dancing around the real issues first
    // but want to evolve the tests to force us to write the correct code
    // rather than just assume what all the code here should be
    // by doing this we will end up with 100% testable code and hopefully no unnecessary lines of code

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test

    public void testSimpleWinSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum);
        assertEquals("You got it", message);
    }

    @Test
    public void testOneWrongNegGuessSituation() {
        String message = game.guess(-5);
        assertEquals("You didn't get it", message);
    }

    @Test
    public void testOneWrongPosGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1);
        assertEquals("You didn't get it", message);
    }

    @Test
    public void testRandomNumberGeneration() {
        // if program runs 100 times you would expect all numbers to come up at least once
        // tells us how far off is the random number generation technique
        // if it generated each of these numbers than we'd store 1 in each of them
        // when we're done with this loop we can run another loop which sums up all the 1's in this array and hopefully we get a 10
        // if it repeats certain numbers that's fine you already have a 1 there and it can be set to 1 again
        // if it misses any numbers then the default of a int is 0 anyway
        // 1 2 3 4 5 6 7 8 9 10
        // 1 1 1 1 0 1 0 1 1 1 = 10
        int[] rndNumCount = new int[11];
        for (int counter=0; counter < 100; counter++) {
            int randomNum = game.getRandomNumber();
            rndNumCount[randomNum] = 1;
        }

        int sum = 0;
        for (int counter=0; counter < 11; counter++) {
            sum += rndNumCount[counter];
        }
        assertEquals(10, sum);
    }
}
