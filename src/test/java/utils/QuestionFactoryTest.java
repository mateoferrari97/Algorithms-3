package utils;

import modelo.Points;
import modelo.options.Option;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class QuestionFactoryTest {
    private QuestionScorer penaltyScorerMock = spy(new PenaltyScorer());
    private Points pointsMock = spy(new Points());

    @Test
    public void testUnmarshalCorrectOption(){
        String jsonString = "";
        OptionFactory optionFactory = new OptionFactory();
        Option option = optionFactory.umarshall(jsonString);

        Assert.assertEquals(option.getText(),"si");

        doNothing().when(penaltyScorerMock).reward(pointsMock);
        option.calculatePoints(penaltyScorerMock,pointsMock);

    }

    @Test
    public void testUnmarshalIncorrectOption(){
        String jsonString = "";
        OptionFactory optionFactory = new OptionFactory();
        Option option = optionFactory.umarshall(jsonString);

        Assert.assertEquals(option.getText(),"si");

        doNothing().when(penaltyScorerMock).punish(pointsMock);
        option.calculatePoints(penaltyScorerMock,pointsMock);
    }
}
