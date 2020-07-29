package modelo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BooleanQuestionPenaltyTest {

    @Test
    public void testBooleanQuestionWithPenaltyDecreasePlayerPointsWhenOptionIsIncorrect() {
        // Given
        List<Option> options = Arrays.asList(new Option("si", new IncorrectOptionScorer()));
        QuestionScorer scorer = new BooleanWithPenaltyScorer();
        Question question = new BooleanQuestion("vamos a aprobar algoritmos 3?", options, scorer);

        Player player = new Player();
        player.setPoints(5);
        Integer expectedPlayerPoints = 4;

        // When
        question.score(player);

        // Then
        Assert.assertEquals(player.getPoints(), expectedPlayerPoints);
    }
}
