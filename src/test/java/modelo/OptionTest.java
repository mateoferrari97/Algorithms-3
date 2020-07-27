package modelo;

import org.junit.Test;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionTest {
    @Test
    public void testBooleanOptionIncreasePlayerPointsWhenOptionIsCorrect() {
        // Given
        Option option = new Option("si", new CorrectScoreBehavior());
        List<Option> options = new LinkedList<>();
        options.add(option);
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options);

        Player player = new Player();
        Integer expectedPlayerPoints = 1;

        // When
        question.score(player, options);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testBooleanOptionDontIncreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        Option option = new Option("no", new IncorrectScoreBehavior());
        List<Option> options = new LinkedList<>();
        options.add(option);
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options);

        Player player = new Player();
        Integer expectedPlayerPoints = 0;

        // When
        question.score(player, options);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testBooleanOptionWithPenaltyDecreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        Option option = new Option("no", new IncorrectScoreWithPenalty());
        List<Option> options = new LinkedList<>();
        options.add(option);
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options);

        Player player = new Player();
        player.setPoints(5);
        Integer expectedPlayerPoints = 4;

        // When
        question.score(player, options);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
