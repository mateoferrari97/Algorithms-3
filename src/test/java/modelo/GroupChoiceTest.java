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
        List<Option> lettersGroup = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()));

        List<Option> numbersGroup = Arrays.asList(
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", lettersGroup, numbersGroup, scorer);

        Player player = new Player();
        Integer expectedPlayerPoints = 1;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testGroupChoiceQuestionDontIncreasePlayerPointsWhenInorrectGroupSeparation() throws InvalidSizeException {

        List<Option> lettersGroup = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()));

        List<Option> numbersGroup = Arrays.asList(
                new Option("C", new CorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));

        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", lettersGroup, numbersGroup, scorer);


        Player player = new Player();
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

}
