package modelo;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionTest {
    @Test
    public void testBooleanOptionIncreasePlayerPointsWhenOptionIsCorrect() {
        // Given
        IScoreBehavior correctScore = new CorrectScoreBehavior();
        Option correctOption = new Option("si", correctScore);

        Player player = new Player();
        Integer expectedPlayerPoints = 1;

        // When
        correctOption.score(player, 1);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testBooleanOptionDontIncreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        IScoreBehavior incorrectScore = new IncorrectScoreBehavior();
        Option incorrectOption = new Option("no", incorrectScore);

        Player player = new Player();
        Integer expectedPlayerPoints = 0;

        // When
        incorrectOption.score(player, 1);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }

    @Test
    public void testBooleanOptionWithPenaltyDecreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        IScoreBehavior incorrectScoreWithPenalty = new IncorrectScoreWithPenalty();
        Option incorrectOption = new Option("si", incorrectScoreWithPenalty);

        Player player = new Player();
        Integer expectedPlayerPoints = 0;

        // When
        incorrectOption.score(player, 1);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
