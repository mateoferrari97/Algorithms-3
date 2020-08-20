package modelo.game;



import modelo.options.CorrectOptionScorer;
import modelo.options.IOptionScorer;
import modelo.options.Option;
import modelo.questions.BooleanQuestion;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({Player.class, BooleanScorer.class, BooleanQuestion.class,ArrayList.class})

public class TurnTest {
    private Player playerMock = spy(new Player());
    private IOptionScorer optionScorer = spy(new CorrectOptionScorer());
    private Option optionMock = spy(new Option("",optionScorer));
    private List<Option> optionsMock = new ArrayList<>();
    private BooleanScorer scorerMock= spy(new BooleanScorer());
    private Question questionMock = spy(new BooleanQuestion( "",optionsMock,scorerMock));

    public Turn setUpTurn(){
        return new Turn(playerMock,questionMock);
    }

    @Test
    public void testGetAmountCurrentOptions(){

        Turn turn = setUpTurn();
        Integer expectedSize = 0;

        Assert.assertEquals(expectedSize, turn.getAmountOfOptions());
    }

    @Test
    public void testGetAmountChosenOptions() {
        when(questionMock.getCorrectOptions()).thenReturn(optionsMock);
        Turn turn = setUpTurn();
        Integer expectedSize = 0;

        Assert.assertEquals(expectedSize, turn.getAmountChosenOptions());
    }

    @Test
    public void testGetAnswers() {
        Turn turn = setUpTurn();

        Assert.assertEquals(0 , turn.getAnswers().size());
    }

    @Test
    public void testAddPlayerAnswer() {
        Turn turn = setUpTurn();

        turn.addPlayerAnswer(optionMock);

        Assert.assertEquals(optionMock,turn.getAnswers().get(0));
    }

    @Test
    public void testGetPlayer() {
        Turn turn = setUpTurn();

        Assert.assertEquals(playerMock,turn.getPlayer());
    }

    @Test
    public void testFinishTurn() {
        Turn turn = setUpTurn();
        doCallRealMethod().when(questionMock).selectOptions(any(ArrayList.class), any(Points.class));

        turn.finish();

        verify(questionMock, times(1)).selectOptions(optionsMock, turn.getPoints());
    }

    @Test
    public void testIncreaseAmounthOfPoints(){
        Turn turn = setUpTurn();
        int amountOfOptions = turn.getAmountCurrentOptions();
        turn.increaseAmountOfCurrentOptions();
        Assert.assertEquals(amountOfOptions+1, turn.getAmountCurrentOptions().intValue());

    }
}
