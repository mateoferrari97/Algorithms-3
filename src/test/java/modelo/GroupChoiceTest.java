package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GroupChoiceTest {
    @Test
    public void testGroupChoiceQuestionIncreasePlayerPointsWhenCorrectGroupSeparation() throws InvalidSizeException {
        // Given
        Player player = new Player();
        Integer expectedPlayerPoints = 1;

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

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testGroupChoiceQuestionDontIncreasePlayerPointsWhenInorrectGroupSeparation() throws InvalidSizeException {

        // Given
        Player player = new Player();
        Integer expectedPlayerPoints = 0;

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

        // When
        question.score(player, playerOptions);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

}
