package modelo.game;

import modelo.options.CorrectOptionScorer;
import modelo.options.IOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.questions.Question;
import modelo.scorers.PenaltyScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

public class UnselectableGroupTest {
    private List<Option> optionsMock = spy(new ArrayList<>());
    private QuestionScorer penaltyScorerMock = spy(new PenaltyScorer());
    private String text = "carlos";
    private Player playerMock = spy(new Player());
    private Question questionMock = spy(new BooleanQuestion(text, optionsMock, penaltyScorerMock));
    private Turn turnMock = spy(new Turn(playerMock, questionMock));
    private IOptionScorer osMock = spy(new CorrectOptionScorer());
    private Option optionMock = spy(new Option("", osMock));

    @Test
    public void TestaddPlayerAnswer() {
        UnselectableGroup usc = new UnselectableGroup();

        doNothing().when(turnMock).increaseAmountOfCurrentOptions();
        usc.addPlayerAnswer(turnMock, optionMock);
    }
}
