package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MultipleChoicePenaltyTest {
    @Test
    public void testMultipleChoiceQuestionWithPenaltyIncreasePlayerPointsWhenOptionIsCorrectAndDecreaseWhenOptionIsIncorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("3 / 1", new IncorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        player.setPoints(7);
        Integer expectedPlayerPoints = 6;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
    @Test
    public void testMultipleChoiceQuestionWithPenaltyIncreasePlayerPointsWhenAllOptionAreCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("3 + 1", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        player.setPoints(7);
        Integer expectedPlayerPoints = 11;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyDontDecreasePlayerPointsWhenAllOptionAreIncorrectAndPlayerHasntPoints() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new IncorrectOptionScorer()),
                new Option("3 + 1", new  IncorrectOptionScorer()),
                new Option("5 - 1", new  IncorrectOptionScorer()),
                new Option("2^2", new  IncorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        player.setPoints(0);
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyPlayerUseDoubleMultiplicatorAndLosesDoubleThePoints() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("3 / 1", new IncorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2 + 1", new IncorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Multiplicator multiplicator = new PenaltyMultiplicator();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, multiplicator);

        Player player = new Player();
        player.setPoints(7);
        Integer expectedPlayerPoints = 1;

        // When
        question.multiplicate(2);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyPlayerUsesTripleMultiplicatorAndWinsDoubleThePoints() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("3 + 1", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Multiplicator multiplicator = new PenaltyMultiplicator();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, multiplicator);

        Player player = new Player();
        Integer expectedPlayerPoints = 12;

        // When
        question.multiplicate(3);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
