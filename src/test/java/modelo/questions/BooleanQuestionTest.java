package modelo.questions;

import modelo.game.Points;
import modelo.options.Option;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static constantes.Constantes.BOOLEAN_QUESTION_TYPE;
import static org.mockito.Mockito.spy;

public class BooleanQuestionTest {
    private List<Option> optionsMock = spy(new ArrayList<>());
    private QuestionScorer penaltyScorerMock = spy(new PenaltyScorer());
    private Points pointsMock = spy(new Points());
    private String text = "carlos";


    @Test
    public void testSelectOptionsOK(){}

    @Test
    public void testGetText(){
        Question booleanQuestion = new BooleanQuestion(text,optionsMock,penaltyScorerMock);
        Assert.assertEquals(text,booleanQuestion.getText());
    }

    @Test
    public void testGetAnswerOptions(){

    }

    @Test
    public void testGetOptions(){
        Question booleanQuestion = new BooleanQuestion(text,optionsMock,penaltyScorerMock);
        Assert.assertEquals(optionsMock,booleanQuestion.getOptions());
    }

    @Test
    public void testGetType(){
        Question booleanQuestion = new BooleanQuestion(text,optionsMock,penaltyScorerMock);
        Assert.assertEquals(BOOLEAN_QUESTION_TYPE,booleanQuestion.getType());
    }

    @Test
    public void testGetMultiplicators(){}
}
