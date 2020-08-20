package modelo;

import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
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
import utils.QuestionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({Player.class, BooleanScorer.class, BooleanQuestion.class, ArrayList.class})

public class RoundIT {
    private  Game gameMock = spy(new Game());
    @Test
    public void testRoundDontDecreasePlayerPointsWhenQuestionHavePenaltyScorerAndPlayerAnswerIncorrecltyWhenPlayerHasntPoints() {
        // Given
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

        //List<Multiplicator> multiplicators = round.getMultiplicator();

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

        round.multiplicate(scoreExclusivity,turnOne);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundQuadruplePointsWithScoreExclusivityTwoTimesActiveWhenQuestionDoesntHaveAPenaltyScorerAndOnePlayerAnswerIncorrectly() throws NoMoreConsumablesException {
        // Given

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

        round.multiplicate(scoreExclusivity,turnOne);
        round.multiplicate(scoreExclusivity,turnTwo);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundDoesntMultiplicatePointsWithScoreExclusivityWhenQuestionDoesntHaveAPenaltyScorerAndBothPlayersAnswerCorrectly() throws NoMoreConsumablesException {
        // Given

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

        round.multiplicate(scoreExclusivity,turnOne);
        round.multiplicate(scoreExclusivity,turnTwo);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testRoundDoubleThePointsToPlayerWhenUsingDoubleMultiplicatorWhenOptionIsCorrectOfAQuestionWithPenalty() throws NoMoreConsumablesException {
        // Given

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


        round.multiplicate(multiplicator,turnTwo);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }
    @Test
    public void testRoundTriplicateThePointsToPlayerWhenUsingTripleMultiplicatorWhenOptionIsCorrectOfAQuestionWithPenalty() throws NoMoreConsumablesException {
        // Given

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

        round.multiplicate(multiplicator,turnTwo);

        round.finish();

        // Then
        Assert.assertEquals(turnOne.getPlayer().getPoints(), expectedPlayerOnePoints);
        Assert.assertEquals(turnTwo.getPlayer().getPoints(), expectedPlayerTwoPoints);
    }

    @Test
    public void testIfOneOnePlayerCorrectAnsweredAndTheOtherBadIt() throws InvalidSizeException, InvalidJsonRecognizerClassException {
        QuestionFactory qFactory = new QuestionFactory();
        Question question = qFactory.unmarshal("{\"text\": \"elegir las opciones que dan como resultado igual a 4\",\"type\": \"MultipleChoiceQuestion\",\"scorer\": \"MultipleChoiceScorer\",\"options\": [{\"text\": \"2 + 2\",\"optionScorer\": true},{\"text\": \"2 * 2\",\"optionScorer\": true},{\"text\": \"1 + 3\",\"optionScorer\": true},{\"text\": \"2^2\",\"optionScorer\": true},{\"text\": \"1 - 3\",\"optionScorer\": false}]}");
        Integer uno= 1;
        Integer cero = 0;
        Player[] players = new Player[2];

        players[0] = new Player(); // este responde bien
        players[1] = new Player(); // este responde mal


        List<Option> playerOneOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2",new CorrectOptionScorer()),
                new Option("1 + 3",new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        List<Option> playerTwoOptions = Arrays.asList(
                new Option("1 - 3", new IncorrectOptionScorer()),
                new Option("2 * 2",new CorrectOptionScorer()),
                new Option("1 + 3",new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        doNothing().when(gameMock).setNextRound();
        Round round = new Round(players, question);
        Turn turnOne = round.getTurn(gameMock);
        Turn turnTwo = round.getTurn(gameMock);

        question.selectOptions(playerOneOptions, turnOne.getPoints());
        question.selectOptions(playerTwoOptions, turnTwo.getPoints());

        round.finish();

        Assert.assertEquals(players[0].getPoints(), uno);
        Assert.assertEquals(players[1].getPoints(), cero);
    }
}
