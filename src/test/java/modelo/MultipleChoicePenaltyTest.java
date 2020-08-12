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
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        player.setPoints(7);
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 8;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
    @Test
    public void testMultipleChoiceQuestionWithPenaltyIncreasePlayerPointsWhenAllOptionAreCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        player.setPoints(7);
        Integer expectedPlayerPoints = 10;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyDontDecreasePlayerPointsWhenAllOptionAreIncorrectAndPlayerHasntPoints() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyPlayerUseDoubleMultiplicatorAndLosesDoubleThePoints() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Multiplicator multiplicator = new x2Multiplicator();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, multiplicator);

        Player player = new Player();
        player.setPoints(7);
        List<Option> playerOptions = Arrays.asList(
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 5;

        // When
        question.multiplicate();
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPenaltyPlayerUsesTripleMultiplicatorAndWinsTripleThePoints() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new PenaltyScorer();
        Multiplicator multiplicator = new x3Multiplicator();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, multiplicator);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 9;

        // When
        question.multiplicate();
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
