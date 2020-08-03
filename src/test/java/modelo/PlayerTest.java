package modelo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testSetAnAmountOfPointsToPlayerAndGetIt(){
        Player player = new Player();
        Integer points = 5;
        player.gainAmountOfPoints(points);

        Assert.assertEquals(player.getPoints(),points);

    }

    @Test
    public void testSetNegativePointsToPlayerAndGetZeroPoints(){
        Player player = new Player();
        Integer points = -5 ;
        Integer zero = 0 ;
        player.gainAmountOfPoints(points);

        Assert.assertEquals(player.getPoints(),zero);

    }


}
