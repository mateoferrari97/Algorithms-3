package utils;

import modelo.Points;
import modelo.options.*;
import modelo.scorers.*;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

public class QuestionFactoryTest {
    private QuestionScorer penaltyScorerMock = spy(new PenaltyScorer());
    private Points pointsMock = spy(new Points());

    @Test
    public void testUnmarshalCorrectOption(){
        String jsonString = "{\"text\": \"si\",\"optionScorer\": true}";
        OptionFactory optionFactory = new OptionFactory();
        Option option = optionFactory.unmarshal(jsonString);

        Assert.assertEquals(option.getText(),"si");

        doNothing().when(penaltyScorerMock).reward(pointsMock);
        option.calculatePoints(penaltyScorerMock,pointsMock);

    }

    @Test
    public void testUnmarshalIncorrectOption(){
        String jsonString = "{\"text\": \"si\",\"optionScorer\": false}";
        OptionFactory optionFactory = new OptionFactory();
        Option option = optionFactory.unmarshal(jsonString);

        Assert.assertEquals(option.getText(),"si");

        doNothing().when(penaltyScorerMock).punish(pointsMock);
        option.calculatePoints(penaltyScorerMock,pointsMock);
    }
}
