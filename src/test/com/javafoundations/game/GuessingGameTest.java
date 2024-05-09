package com.javafoundations.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GuessingGameTest {

    @Test
    public void testSimpleWinSituation() {
        GuessingGame game = new GuessingGame();
        String message = game.guess(3);
        assertEquals("You got it", message);
    }
}
