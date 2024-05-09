package com.javafoundations.game;

public class GuessingGame {
    public String guess(int guessedNumber) {
        return guessedNumber == getRandomNumber() ? "You got it" : "You didn't get it";
    }

    public int getRandomNumber() {
        return 0;
    }
}
