package modelo.game;

import consumables.Consumable;
import consumables.ScoreExclusivity;
import modelo.Game;
import modelo.Player;
import modelo.Round;
import modelo.Turn;
import modelo.options.*;
import modelo.questions.*;
import modelo.scorers.*;

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
@PrepareForTest({Player.class, BooleanScorer.class,BooleanQuestion.class,ArrayList.class})


public class RoundTest {
    private Game gameMock = spy(new Game());
    private Player playerMock = spy(new Player());
    private List<Option> optionsMock = spy(new ArrayList<>());
    private BooleanScorer scorerMock= spy(new BooleanScorer());
    private Consumable consumable = spy(new ScoreExclusivity());
    private Question questionMock = spy(new BooleanQuestion( "",optionsMock,scorerMock, consumable));

    public Round setUpRound(){
        Player[] players = new Player[]{playerMock,playerMock};
        return new Round(players,questionMock);
    }
   @Test
    public void testGetTurn(){
        doNothing().when(gameMock).setNextRound();
        Round round = setUpRound();
        Turn turn = round.getTurn(gameMock);

        Assert.assertNotEquals(turn,round.getTurn(gameMock));

    }

    @Test
    public void testGetQuestionFromARound(){
        when(questionMock.getText()).thenReturn("hola");
        Round round = setUpRound();

        Question question = round.getQuestion();
        Assert.assertEquals(question.getText(),"hola");

    }

}