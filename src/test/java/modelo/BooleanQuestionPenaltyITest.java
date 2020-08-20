package modelo;


import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.multiplicators.Multiplicator;
import modelo.questions.Question;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BooleanQuestionPenaltyITest {

    @Test
    public void testBooleanQuestionWithPenaltyDecreasePointsWhenOptionIsIncorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new PenaltyScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        Integer expectedPoints = -1;

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testBooleanQuestionWithPenaltyIncreasePointsWhenOptionIsCorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new PenaltyScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
}
