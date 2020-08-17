package modelo.game;

import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.scorers.*;
import modelo.questions.*;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({Player.class, BooleanScorer.class, BooleanQuestion.class,ArrayList.class})

public class GameTest {
    Game game = new Game();

    @Test
    public void getNextRound() throws InvalidSizeException, IOException, InvalidJsonRecognizerClassException {
        game.init();
        Round round = game.getNextRound();

        Assert.assertEquals(round, game.getNextRound());
    }

    @Test
    public void setNextRound() throws InvalidSizeException, IOException, InvalidJsonRecognizerClassException {
        game.init();
        Round round = game.getNextRound();

        game.setNextRound();

        Assert.assertNotEquals(round, game.getNextRound());
    }

    @Test
    public void getNextPlayer() throws InvalidSizeException, IOException, InvalidJsonRecognizerClassException {
        game.init();
        Player player = game.getNextPlayer();

        Assert.assertNotEquals(player, game.getNextPlayer());
    }

    @Test
    public void getNextPlayerReturnNullIfThereIsNotMorePlayersInTheListOfGame() throws InvalidSizeException, IOException, InvalidJsonRecognizerClassException {
        game.init();
        Player playerOne = game.getNextPlayer();
        Player playerTwo = game.getNextPlayer();

        Assert.assertNull(game.getNextPlayer());
    }

    @Test
    public void testGet2PlayersFromTheGame() throws InvalidJsonRecognizerClassException, InvalidSizeException, IOException {
        game.init();
        Player[] players = game.getPlayers();
        Assert.assertEquals(players.length, 2);
    }
}
