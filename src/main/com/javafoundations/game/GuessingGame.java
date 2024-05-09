package com.javafoundations.game;

import java.util.Random;

public class GuessingGame {
    private final int randomNumber = new Random().nextInt(10) + 1;
    private int counter = 0;

    public String guess(int guessedNumber) {
        counter++;
        String tryText = counter == 1 ? "try" : "tries";
        String winningMsg = String.format("You got it in %d %s", counter, tryText);
        String response = null;

        if (counter == 4 && guessedNumber != getRandomNumber()) {
            response = String.format("You didn't get it and you've had %d %s. Game over.", counter, tryText);
        } else if (counter > 4) {
            response = "Sorry you are limited to only 4 tries. Your game is over.";
        } else {
            response = guessedNumber == getRandomNumber() ? winningMsg : "You didn't get it";
        }
        return response;
    }

    // Getter method
    public int getRandomNumber() {
        return randomNumber;
    }
}

// NOTES
// best practices suggest avoiding multiple return statements in a method
// ok to do the most minimal thing to pass the test
// similar functionality as previous implementation but much simpler code overall and nothing here is extraneous whatsoever.
// this may be because it is just focused on the game logic, no interaction with the console, or accepting input from a user.

// With a good set of unit tests in place you can have the confidence to go back in and refactor things, to know if you've broken anything after a small change.
// Conversely it is advised not to refactor code until you have a strong suite of tests that validate all of the functionality is working as it should before you do the refactoring.