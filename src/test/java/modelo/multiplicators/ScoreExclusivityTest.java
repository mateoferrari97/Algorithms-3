package modelo.multiplicators;

import org.junit.Assert;
import org.junit.Test;

import static constantes.Constantes.SCORE_EXCLUSIVITY_TEXT;

public class ScoreExclusivityTest {
    private ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

    @Test
    public void testGetTextOfTheScoreExclusivityShouldBeTheSameAsConstants(){

        Assert.assertEquals(scoreExclusivity.getText(), SCORE_EXCLUSIVITY_TEXT );
    }
}
