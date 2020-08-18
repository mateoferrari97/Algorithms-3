package modelo;


import exceptions.InvalidSizeException;
import exceptions.NoMoreConsumablesException;
import modelo.game.Player;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.MultipleChoiceScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

public class MultipleChoiceIT {

    @Test
    public void testMultipleChoiceQuestionIncreasePlayerPointsWhenAllOptionsAreCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionDontIncreasePlayerPointsForIncorectOption() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 0;

        // When
        question.selectOptions(playerOptions, this.points);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMultipleChoiceQuestionReceivesAOneOptionListAndThrowsException() throws InvalidSizeException {
        //Arrange
        List<Option> options = Arrays.asList(new Option("Malasia", new CorrectOptionScorer()));
        QuestionScorer scorer = new MultipleChoiceScorer();

        //Act and Assert
        thrown.expect(InvalidSizeException.class);
        Question question = new MultipleChoiceQuestion("Elegir los paises pertenecientes al continente asiatico", options, scorer);

    }

    @Test
    public void testMultipleChoiceQuestionReceivesASixOptionListAndThrowsException() throws InvalidSizeException {
        //Arrange
        List<Option> options = Arrays.asList(
                new Option("Malasia", new CorrectOptionScorer()),
                new Option("China", new CorrectOptionScorer()),
                new Option("Francia", new IncorrectOptionScorer()),
                new Option("Laos", new CorrectOptionScorer()),
                new Option("Vietnam", new CorrectOptionScorer()),
                new Option("Japon", new CorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();

        //Act and Assert
        thrown.expect(InvalidSizeException.class);
        Question question = new MultipleChoiceQuestion("Elegir los paises pertenecientes al continente asiatico", options, scorer);

    }

    @Test
    public void testMultipleChoiceDoublePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given

        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 2;


        // When
        question1.selectOptions(player1Options, this.points);
        question2.selectOptions(player2Options, this.points);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testMultipleChoiceQuadruplePointsWhenScoreExclusivityActivatedAndOnePlayerAnswerIncorrectly() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 4;

        // When
        question1.selectOptions(player1Options, this.points);
        question2.selectOptions(player2Options, this.points);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }

    @Test
    public void testMultipleChoiceDontModifyPointsWhenScoreExclusivityActivatedNoIncorrectAnswers() throws InvalidSizeException, NoMoreConsumablesException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question1 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);
        Question question2 = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        List<Option> player1Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        List<Option> player2Options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()));

        Player player1 = new Player();
        Player player2 = new Player();
        Integer expectedPlayer1Points = 0;
        Integer expectedPlayer2Points = 0;

        // When
        question1.selectOptions(player1Options, this.points);
        question2.selectOptions(player2Options, this.points);
        question1.score(player1);
        question2.score(player2);

        // Then
        Assert.assertEquals(player1.getPoints(), expectedPlayer1Points);
        Assert.assertEquals(player2.getPoints(), expectedPlayer2Points);
    }
}
