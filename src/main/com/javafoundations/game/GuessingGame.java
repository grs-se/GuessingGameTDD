// fully qualified class name
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
            String tooHighLowText = null;
            if (guessedNumber < getRandomNumber()) {
                tooHighLowText = "- you're too low";
            } else if (guessedNumber > getRandomNumber()) {
                tooHighLowText = "- you're too high";
            } else {
                tooHighLowText = "";
            }
            String lostText = String.format("You didn't get it %s", tooHighLowText).trim();
            response = guessedNumber == getRandomNumber() ? winningMsg : lostText;
        }
        return response;
    }

    // Getter method
    public int getRandomNumber() {
        return randomNumber;
    }

    // Game logic seperate from the UI logic
    // UI logic could have been put in another class
    // java -cp .\out\production\GuessingGameTDD com.javafoundations.game.GuessingGame
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        boolean loopShouldContinue = true;
        do {
            String input = System.console().readLine("Enter a number: ");
            if ("q".equals(input)) {
                return;
            }
            String output = game.guess(Integer.parseInt(input));
            System.out.println(output);
            if (output.contains("You got it") || output.contains("over")) {
                loopShouldContinue = false;
            }
        } while (loopShouldContinue);
    }
}

// NOTES ON TDD
// best practices suggest avoiding multiple return statements in a method
// ok to do the most minimal thing to pass the test
// similar functionality as previous implementation but much simpler code overall and nothing here is extraneous whatsoever.
// this may be because it is just focused on the game logic, no interaction with the console, or accepting input from a user.

// With a good set of unit tests in place you can have the confidence to go back in and refactor things, to know if you've broken anything after a small change.
// Conversely it is advised not to refactor code until you have a strong suite of tests that validate all of the functionality is working as it should before you do the refactoring.