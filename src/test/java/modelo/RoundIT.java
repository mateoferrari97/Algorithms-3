package modelo;

import exceptions.NoMoreConsumablesException;
import modelo.game.Game;
import modelo.game.Player;
import modelo.game.Round;
import modelo.game.Turn;
import modelo.multiplicators.Multiplicate;
import modelo.multiplicators.Multiplicator;
import modelo.multiplicators.ScoreExclusivity;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({Player.class, BooleanScorer.class, BooleanQuestion.class, ArrayList.class})

public class RoundIT {

    @Test
    public void testRoundDontDecreasePlayerPointsWhenQuestionHavePenaltyScorerAndPlayerAnswerIncorrecltyWhenPlayerHasntPoints() {
        // Given
        Game gameMock = spy(new Game());
        doNothing().when(gameMock).setNextRound();

        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new PenaltyScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        Player[] players = new Player[2];

        players[0] = new Player();
        players[1] = new Player();

        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);


        List<Option> playerOneOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));

        Integer expectedPlayerOnePoints = 0;
        Integer expectedPlayerTwoPoints = 0;

        //When
        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundDoublePointsWithScoreExclusivityWhenQuestionDoesntHaveAPenaltyScorerAndOnePlayerAnswerIncorrectly() throws NoMoreConsumablesException {
        // Given

        Game gameMock = spy(new Game());
        doNothing().when(gameMock).setNextRound();


        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();

        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);


        Player[] players = new Player[2];

        players[0] = new Player();
        players[1] = new Player();

        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);


        List<Option> playerOneOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));

        Integer expectedPlayerOnePoints = 0;
        Integer expectedPlayerTwoPoints = 2;

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        turnTwo.multiplicate(scoreExclusivity);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundQuadruplePointsWithScoreExclusivityTwoTimesActiveWhenQuestionDoesntHaveAPenaltyScorerAndOnePlayerAnswerIncorrectly() throws NoMoreConsumablesException {
        // Given

        Game gameMock = spy(new Game());
        doNothing().when(gameMock).setNextRound();


        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();

        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);


        Player[] players = new Player[2];

        players[0] = new Player();
        players[1] = new Player();

        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);


        List<Option> playerOneOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));

        Integer expectedPlayerOnePoints = 0;
        Integer expectedPlayerTwoPoints = 4;

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        turnOne.multiplicate(scoreExclusivity);
        turnTwo.multiplicate(scoreExclusivity);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundDoesntMultiplicatePointsWithScoreExclusivityWhenQuestionDoesntHaveAPenaltyScorerAndBothPlayersAnswerCorrectly() throws NoMoreConsumablesException {
        // Given

        Game gameMock = spy(new Game());
        doNothing().when(gameMock).setNextRound();


        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();

        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);


        Player[] players = new Player[2];

        players[0] = new Player();
        players[1] = new Player();

        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);


        List<Option> playerOneOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));

        Integer expectedPlayerOnePoints = 1;
        Integer expectedPlayerTwoPoints = 1;

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        turnOne.multiplicate(scoreExclusivity);
        turnTwo.multiplicate(scoreExclusivity);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundDoubleThePointsToPlayerWhenUsingDoubleMultiplicatorWhenOptionIsCorrectOfAQuestionWithPenalty() throws NoMoreConsumablesException {
        // Given

        Game gameMock = spy(new Game());
        doNothing().when(gameMock).setNextRound();


        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new PenaltyScorer();

        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);


        Player[] players = new Player[2];

        players[0] = new Player();
        players[1] = new Player();

        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);


        List<Option> playerOneOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));

        Integer expectedPlayerOnePoints = 0;
        Integer expectedPlayerTwoPoints = 2;

        Multiplicate multiplicator  = new Multiplicate("X2", 2);

        //When
        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        turnTwo.multiplicate(multiplicator);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }
    @Test
    public void testRoundTriplicateThePointsToPlayerWhenUsingTripleMultiplicatorWhenOptionIsCorrectOfAQuestionWithPenalty() throws NoMoreConsumablesException {
        // Given

        Game gameMock = spy(new Game());
        doNothing().when(gameMock).setNextRound();


        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new PenaltyScorer();

        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);


        Player[] players = new Player[2];

        players[0] = new Player();
        players[1] = new Player();

        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);


        List<Option> playerOneOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));

        Integer expectedPlayerOnePoints = 0;
        Integer expectedPlayerTwoPoints = 3;

        Multiplicate multiplicator  = new Multiplicate("X3", 3);

        //When
        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        turnTwo.multiplicate(multiplicator);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }
}
