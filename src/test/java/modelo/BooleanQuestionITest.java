package modelo;


import exceptions.NoMoreConsumablesException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.multiplicators.Multiplicator;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BooleanQuestionITest {

    @Test
    public void testBooleanQuestionIncreasePointsWhenOptionIsCorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        List<Option> playerOptions = Arrays.asList(new Option("si", new CorrectOptionScorer()));
        Integer expectedPoints = 1;

        Points points = new Points();

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testBooleanQuestionDontIncreasePointsWhenOptionIsIncorrect() {
        // Given
        List<Option> options = Arrays.asList(
                new Option("si", new CorrectOptionScorer()),
                new Option("no", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);


        List<Option> playerOptions = Arrays.asList(new Option("no", new IncorrectOptionScorer()));
        Integer expectedPoints = 0;

        Points points = new Points();

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
}
