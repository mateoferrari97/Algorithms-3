package modelo.multiplicators;


import modelo.game.Player;
import modelo.game.Points;
import modelo.questions.BooleanQuestion;
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

public class MultiplicateTest {

    @Test
    public void getText() {
        Multiplicate multiplicate = new Multiplicate("texto", 2);

        String expectedString = "texto";


        Assert.assertEquals(expectedString, multiplicate.getText());
    }

   /* @Test
    public void multiplicate(){

        Points pointMock = spy(new Points());

        doNothing().when(pointMock).multiplicate(any(Integer.class));

        Multiplicate multiplicate = new Multiplicate("texto", 2);

        multiplicate.multiplicate(pointMock);

        verify(pointMock, times(1));
    } */

}
