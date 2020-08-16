package modelo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
@RunWith(PowerMockRunner.class)
@PrepareForTest({Player.class})
public class PointsTest {
    private Player playerMock = spy(new Player());

    @Test
    public void testIncreasePointsAndGiveItToPlayer(){
        try {
            doNothing().when(playerMock).gainAmountOfPoints(1);
            Points points = new Points();
            points.increasePoints();
            points.givePointsToPlayer(playerMock);

        }catch(Exception e){
            fail("unexpected failure in testIncreasePointsAndGiveItToPlayer");
        }
    }

    @Test
    public void testDecreasePointsAndGiveItToPlayer(){
        try {
            doNothing().when(playerMock).gainAmountOfPoints(-1);
            Points points = new Points();
            points.decreasePoints();
            points.givePointsToPlayer(playerMock);

        }catch(Exception e){
            fail("unexpected failure in testDecreasePointsAndGiveItToPlayer");
        }
    }

    @Test
    public void testChangeToZeroPoints(){
        try {
            doNothing().when(playerMock).gainAmountOfPoints(0);
            Points points = new Points();
            points.changeScoreToZero();
            points.givePointsToPlayer(playerMock);

        }catch(Exception e){
            fail("unexpected failure in testDecreasePointsAndGiveItToPlayer");
        }
    }

}
