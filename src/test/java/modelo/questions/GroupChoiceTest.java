package modelo.questions;

import exceptions.InvalidSizeException;
import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.BooleanScorer;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({ArrayList.class})

public class GroupChoiceTest {
    private List<Option> optionsMock = spy(new ArrayList<>());
    private QuestionScorer penaltyScorerMock = spy(new PenaltyScorer());
    private Points pointsMock = spy(new Points());
    private QuestionScorer scorerMock = spy(new BooleanScorer());
    private String text = "carlos";



    @Test
    public void testSelectOptionsOK(){}

    @Test
    public void testGetText() throws InvalidSizeException {
        doReturn(3).when(optionsMock).size();
        Question groupChoiceQuestion = new GroupChoiceQuestion(text,optionsMock,penaltyScorerMock);
        Assert.assertEquals(text,groupChoiceQuestion.getText());
    }

    @Test
    public void testGetAnswerOptions(){}

    @Test
    public void testGetOptions(){}

    @Test
    public void testGetType() throws InvalidSizeException {
        doReturn(3).when(optionsMock).size();
        Question groupChoiceQuestion = new GroupChoiceQuestion(text,optionsMock,penaltyScorerMock);
        Assert.assertEquals(text,groupChoiceQuestion.getText());}

    @Test
    public void testGetMultiplicators(){}
    @Test
    public void testThrowInvalidSizeExceptionWhenTheOptionsAre6() {
        doReturn(6).when(optionsMock).size();
        try {
            Question question = new GroupChoiceQuestion("Separar en numeros y letras", optionsMock, scorerMock);

        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"invalid options size: want minimum 2, maximum 6. got: 6");
        }
    }
}
