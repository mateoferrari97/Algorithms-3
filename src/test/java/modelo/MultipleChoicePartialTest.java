package modelo;

import exceptions.InvalidSizeException;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.MultipleChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.MultipleChoiceWithPartialScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MultipleChoicePartialTest {
    @Test
    public void testMultipleChoiceQuestionWithPartialScoreIncreasePlayerPointsForeachOptionThatIsCorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("5 - 1", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 4;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testMultipleChoiceQuestionWithPartialScoreIncreasePlayerPointsFor3CorrectOptionAndOneIncorrect() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("2^2", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));

        QuestionScorer scorer = new MultipleChoiceWithPartialScorer();
        Question question = new MultipleChoiceQuestion("elegir las opciones que dan como resultado igual a 4", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(
                new Option("2 + 2", new CorrectOptionScorer()),
                new Option("2 * 2", new CorrectOptionScorer()),
                new Option("1 + 3", new CorrectOptionScorer()),
                new Option("1 - 3", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
