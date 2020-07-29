package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MultupleChoicePenaltyTest {
    @Test
    public void testMultipleChoiceQuestionWithPenaltyIncreasePlayerPointsWhenOptionIsCorrectAndDecreaseWhenOptionIsIncorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("3 / 1", new IncorrectOptionScorer()),
                new Option("5 / 1", new IncorrectOptionScorer()),
                new Option("1 / 1", new IncorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceWithPenaltyScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        player.setPoints(7);
        Integer expectedPlayerPoints = 6;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}