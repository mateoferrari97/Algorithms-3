package modelo;

import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BooleanQuestionTest {

    @Test
    public void testBooleanQuestionIncreasePlayerPointsWhenOptionIsCorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        Player player = new Player();
        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPlayerPoints = 1;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testBooleanQuestionDontIncreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        Player player = new Player();
        player.setPoints(5);
        List<Option> playerOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        Integer expectedPlayerPoints = 5;

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

}
