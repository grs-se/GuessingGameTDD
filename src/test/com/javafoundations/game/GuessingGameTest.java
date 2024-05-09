package com.javafoundations.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class GuessingGameTest {
    public static final int MAX_RANDOMNESS_RETRIES = 100;
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
        assertEquals("You got it in 1 try", message);
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
//    @RepeatedTest(10)
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
        for (int counter = 0; counter < MAX_RANDOMNESS_RETRIES; counter++) {
            // ambiguity, same variable name as field, by default Java will use the most local variable
            // want new instance of class to get new random num each loop
            GuessingGame game = new GuessingGame();
            int randomNum = game.getRandomNumber();
            rndNumCount[randomNum] = 1;
        }

        int sum = 0;
        for (int counter=0; counter < 11; counter++) {
            sum += rndNumCount[counter];
        }
        assertEquals(10, sum);
    }
    // don't write unit tests for other people's code, just our code
    // so don't want to test Java's ability to generate a random num but instead our ability to use that random num

    @Test
    public void testFourWrongGuesses() {
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        String message = game.guess(-3);
        assertEquals("You didn't get it and you've had 4 tries. Game over.", message);
    }

    @Test
    public void testThreeWrongAndOneCorrect() {
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        assertEquals("You got it in 4 tries", message);
    }

    // One of the interesting things about TDD is that it is an odd way to code in some respects because
    // if you are doing it strictly where you' just trying to implement what you think might be the simplest thing you can do in the code to get the test to pass
    // then sometimes that results in you writing code that you yourself might not have intentionally wanted to write yet it does actually make the tests pass
    //

    @Test
    public void testTwoWrongGuessesAndOneCorrect() {
        game.guess(-3);
        game.guess(-3);
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        assertEquals("You got it in 3 tries", message);
    }

    // "Self-Documenting" code:
    // comments have a tendency to eventually over time fall out of sync with the code that they are commenting
    // whereas method names have a harder time getting out of sync

    // "Fragile Tests"
    // boy who cried wolf - if a test keeps failing there may be a tendency to disable it but not a good idea: best to address why it is the test is failing
}
