package modelo;


import exceptions.InvalidSizeException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.multiplicators.Multiplicator;
import modelo.questions.Question;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MultipleChoicePenaltyITest {
    @Test
    public void testMultipleChoiceQuestionWithPenaltyIncreasePointsWhenOptionIsCorrectAndDecreaseWhenOptionIsIncorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Points points = new Points();

        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()));

        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
    @Test
    public void testMultipleChoiceQuestionWithPenaltyIncreasePointsWhenAllOptionAreCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));


        Integer expectedPoints = 3;

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyDecreasePointsWhenAllOptionAreIncorrectAndPlayer() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Points points = new Points();
        points.increasePoints();
        List<Option> playerOptions = Arrays.asList(
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()));
        Integer expectedPoints = 0;

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
}
