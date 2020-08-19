package modelo;


import exceptions.InvalidSizeException;
import exceptions.NoMoreConsumablesException;
import modelo.game.Player;
import modelo.game.Points;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.GroupChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GroupChoiceIT {
    @Test
    public void testGroupChoiceQuestionIncreasePointsWhenCorrectGroupSeparation() throws InvalidSizeException {
        // Given
        Integer expectedPoints = 1;

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));


        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer);

        List<Option> playerOptions = Arrays.asList(
                options.get(3),
                options.get(4));

        Points points = new Points();

        // When
        question.selectOptions(playerOptions, points);


        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testGroupChoiceQuestionDontIncreasePointsWhenInorrectGroupSeparation() throws InvalidSizeException {

        // Given
        Integer expectedPoints = 0;

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer);


        List<Option> playerOptions = Arrays.asList(
                options.get(0),
                options.get(1),
                options.get(3));

        Points points = new Points();

        // When
        question.selectOptions(playerOptions, points);

        // Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
}
