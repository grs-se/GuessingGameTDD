package com.javafoundations.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals("You didn't get it - you're too low", message);
    }

    @Test
    public void testOneWrongPosGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1);
        assertEquals("You didn't get it - you're too high", message);
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

    @Test
    public void testTwoWrongGuessesAndOneCorrect() {
        game.guess(-3);
        game.guess(-3);
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        // message to help you pinpoint which of the assertions is failing
        assertTrue(message.contains(" 3 "), "Should indicate 3 tries");
        assertTrue(message.contains("You got it"), "Should indicate that we got the right number of tries");
//        assertEquals("You got it in 3 tries", message);
    }

    @Test
    public void testTenWrongGuesses() {
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);;
        String message = game.guess(-3);
        assertEquals("Sorry you are limited to only 4 tries. Your game is over.", message);
    }

    @Test
    // this will pass but it is not a test without an assertion
    public void junkTest() {
    }

    // NOTES:

    // One of the interesting things about TDD is that it is an odd way to code in some respects because
    // if you are doing it strictly where you' just trying to implement what you think might be the simplest thing you can do in the code to get the test to pass
    // then sometimes that results in you writing code that you yourself might not have intentionally wanted to write yet it does actually make the tests pass

    // don't write unit tests for other people's code, just our code
    // so don't want to test Java's ability to generate a random num but instead our ability to use that random num

    // "Self-Documenting" code:
    // comments have a tendency to eventually over time fall out of sync with the code that they are commenting
    // whereas method names have a harder time getting out of sync

    // "Fragile Tests"
    // boy who cried wolf - if a test keeps failing there may be a tendency to disable it but not a good idea: best to address why it is the test is failing

    // limits changes to 1 at a time, make all tests work and then change again. Don't make multiple changes at once!


    // DEBUGGING
    // put breakpoint on line close to where we think things are going awry
    // stepping in to a method (like the thread will step into it)
    // going along for the ride with the thread
    // Call stack - thread goes from method to method, each method's parameters get placed on something called a stack
    // halted each step
    // don't want to step into everything - can get into trouble, need to know when to step in and step over
    // the line doesn't execute until after you step in or over it
    // convenient to be able to see in real time what the program is actually doing, what values are set where.
    // stepping into the string.format method is not useful to us. Code written by Java or third party problems you should assume works fine. a waste of our time
    // can't just change something during a debugging session. Java is compiled, if we make changes to code, code has to be recompiled, and reloaded into the memory, into the debugging session itself.
    // debugging session after changes will be in unknown state
    // might not want to have to start again. Want to make a surgical change and then pick up where you left off
    // drop or reset a frame, restart from this frame (which mught be 10 frames deep), rather from entire program.
    // dropping a frame does not reset the values of fields in a class, all it does at best is reset the local vairables in a method
    // do not need to recompile after changing the value of a variable in debugging mode, as this is not the code, it is metadata but may need to drop the frame.
}
