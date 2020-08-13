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
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new Multiplicator());

        Player player = new Player();
        player.setPoints(7);
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 8;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

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
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new Multiplicator());

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        player.setPoints(7);
        Integer expectedPlayerPoints = 10;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

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
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, new Multiplicator());

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 0;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

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
        Multiplicator multiplicator = new Multiplicator();
        multiplicator.setFactor(2);
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, multiplicator);

        Player player = new Player();
        player.setPoints(7);
        List<Option> playerOptions = Arrays.asList(
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 5;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

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
        Multiplicator multiplicator = new Multiplicator();
        multiplicator.setFactor(3);
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer, multiplicator);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 9;

        // When
        question.selectOptions(playerOptions);
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
