package modelo;


import exceptions.InvalidSizeException;
import exceptions.NoMoreConsumablesException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.OrderedChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.OrderedScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OrderedChoiceITest {
    @Test
    public void testOrderedChoiceQuestionIncreasePointsWhenOptionsAreInTheCorrectOrder() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = options;
        Integer expectedPoints = 1;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testOrderedChoiceQuestionDoesNotIncreasePointsWhenOptionsAreInTheIncorrectOrder() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);

        Points points = new Points();
        List<Option> playerOptions = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3),
                options.get(2));
        Integer expectedPoints = 0;

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
    @Test
    public void testGetCorrectOptionsFromOrderedChoiceQuestion() throws InvalidSizeException {
        // Given
        List<Option> opt = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", opt, scorer);

        List<Option> options = question.getCorrectOptions();
        Assert.assertEquals(opt,options);
    }
}
