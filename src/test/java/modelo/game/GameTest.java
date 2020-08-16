package modelo.game;

import exceptions.InvalidSizeException;
import modelo.Game;
import modelo.Player;
import modelo.Round;
import modelo.scorers.*;
import modelo.questions.*;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({Player.class, BooleanScorer.class, BooleanQuestion.class,ArrayList.class})

public class GameTest {
    Game game = new Game();

    @Test
    public void getNextRound() throws InvalidSizeException {
        game.init();
        Round round = game.getNextRound();

        Assert.assertEquals(round, game.getNextRound());
    }

    @Test
    public void setNextRound() throws InvalidSizeException {
        game.init();
        Round round = game.getNextRound();

        game.setNextRound();

        Assert.assertNotEquals(round, game.getNextRound());
    }

    @Test
    public void getNextPlayer() throws InvalidSizeException {
        game.init();
        Player player = game.getNextPlayer();

        Assert.assertNotEquals(player, game.getNextPlayer());
    }

    @Test
    public void getNextPlayerReturnNullIfThereIsNotMorePlayersInTheListOfGame() throws InvalidSizeException {
        game.init();
        Player playerOne = game.getNextPlayer();
        Player playerTwo = game.getNextPlayer();

        Assert.assertNull(game.getNextPlayer());
    }
}
