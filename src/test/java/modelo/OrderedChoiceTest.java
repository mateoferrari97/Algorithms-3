package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OrderedChoiceTest {
    /*@Test
    public void testOrderedChoiceQuestionIncreasePlayerPointsWhenOptionsAreInTheCorrectOrder() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);

        Player player = new Player();
        List<Integer> playerAnswers = Arrays.asList(0,1,2,3);
        Integer expectedPlayerPoints = 1;

        // When
        question.score(player,playerAnswers);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testOrderedChoiceQuestionDoesNotIncreasePlayerPointsWhenOptionsAreInTheIncorrectOrder() throws InvalidSizeException {
        // Given
        List<Option> options = Arrays.asList(
                new Option("Primero", new CorrectOptionScorer()),
                new Option("Segundo", new IncorrectOptionScorer()),
                new Option("Tercero", new IncorrectOptionScorer()),
                new Option("Cuarto", new IncorrectOptionScorer()));

        QuestionScorer scorer = new OrderedScorer();
        Question question = new OrderedChoiceQuestion("ordene correctamente las siguientes opciones", options, scorer);

        Player player = new Player();
        List<Integer> playerAnswers = Arrays.asList(0,1,3,2);
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player,playerAnswers);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }*/
}
