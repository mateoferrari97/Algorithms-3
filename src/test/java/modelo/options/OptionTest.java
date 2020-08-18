package modelo.options;

import modelo.game.Points;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({QuestionScorer.class, Points.class})
public class OptionTest {
    QuestionScorer scorerMock = spy(new BooleanScorer());
    Points pointsMock = spy(new Points());
    @Test
    public void testCalculatePointsShouldBeMakeQuestionsScorerRewardPoints(){
        doNothing().when(scorerMock).reward(pointsMock);
        IOptionScorer trueOption = new CorrectOptionScorer();
        Option option = new Option("",trueOption);
        option.calculatePoints(scorerMock,pointsMock);
    }
    @Test
    public void testCalculatePointsShouldBeMakeQuestionsScorerPunusgPoints(){
        doNothing().when(scorerMock).punish(pointsMock);
        IOptionScorer trueOption = new IncorrectOptionScorer();
        Option option = new Option("",trueOption);
        option.calculatePoints(scorerMock,pointsMock);
    }
    @Test
    public void testGetTextShouldReturnTheText(){
        String name = "carlos";
        IOptionScorer trueOption = new CorrectOptionScorer();
        Option option = new Option(name,trueOption);
        Assert.assertEquals(name,option.getText());

    }

    @Test
    public void testIsCorrectOptionWhenIsCorrectShouldBeTrue(){
        IOptionScorer trueOption = new CorrectOptionScorer();
        Option option = new Option("",trueOption);
        Assert.assertTrue(option.isCorrect());

    }
    @Test
    public void testIsCorrectOptionWhenIsIncorrectShouldBeFalse(){
        IOptionScorer trueOption = new IncorrectOptionScorer();
        Option option = new Option("",trueOption);
        Assert.assertFalse(option.isCorrect());

    }

    @Test
    public void testIsCorrectOptionWhenIsCorrectAndChangeToIncorrectShouldBeFalse(){
        IOptionScorer trueOption = new CorrectOptionScorer();
        Option option = new Option("",trueOption);
        Assert.assertTrue(option.isCorrect());

        option.changeToIncorrect();
        Assert.assertFalse(option.isCorrect());

    }
    @Test
    public void testIsCorrectOptionWhenIsIncorrectAndChangeToIncorrectShouldBeTrue(){
        IOptionScorer trueOption = new IncorrectOptionScorer();
        Option option = new Option("",trueOption);
        Assert.assertFalse(option.isCorrect());

        option.changeToCorrect();
        Assert.assertTrue(option.isCorrect());

    }
}
