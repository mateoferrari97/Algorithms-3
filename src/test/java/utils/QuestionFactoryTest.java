package utils;

import modelo.Points;
import modelo.options.Option;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({QuestionScorer.class, Points.class})
public class QuestionFactoryTest {
    private QuestionScorer penaltyScorerMock = Mockito.spy(new PenaltyScorer());
    private Points pointsMock = Mockito.spy(new Points());

    @Test
    public void testUnmarshalCorrectOption(){
        String jsonString = "{\"text\": \"si\",\"optionScorer\": true}";
        OptionFactory optionFactory = new OptionFactory();
        Option option = optionFactory.unmarshal(jsonString);

        Assert.assertEquals(option.getText(),"si");

        Mockito.doNothing().when(penaltyScorerMock).reward(pointsMock);
        option.calculatePoints(penaltyScorerMock,pointsMock);

    }

    @Test
    public void testUnmarshalIncorrectOption(){
        String jsonString = "{\"text\": \"si\",\"optionScorer\": false}";
        OptionFactory optionFactory = new OptionFactory();
        Option option = optionFactory.unmarshal(jsonString);

        Assert.assertEquals(option.getText(),"si");

        Mockito.doNothing().when(penaltyScorerMock).punish(pointsMock);
        option.calculatePoints(penaltyScorerMock,pointsMock);
    }
}
