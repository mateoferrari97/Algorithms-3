package modelo;


import exceptions.InvalidSizeException;
import modelo.game.Points;
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

public class MultipleChoiceITest {

    @Test
    public void testMultipleChoiceQuestionIncreasePointsWhenAllOptionsAreCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));

        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testMultipleChoiceQuestionDontIncreasePointsForIncorectOption() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        Integer expectedPoints = 0;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
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
}
