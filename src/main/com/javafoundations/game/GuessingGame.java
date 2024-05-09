package com.javafoundations.game;

import java.util.Random;

public class GuessingGame {
    private final int randomNumber = new Random().nextInt(10) + 1;
    private int counter = 0;

    public String guess(int guessedNumber) {
        counter++;
        if (counter == 4 && guessedNumber != getRandomNumber()) {
            return "You didn't get it and you've had four tries. Game over.";
        }
        String tryText = counter == 1 ? "try" : "tries";
        String winningMsg = String.format("You got it in %d %s", counter, tryText);
        return guessedNumber == getRandomNumber() ? winningMsg : "You didn't get it";
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
