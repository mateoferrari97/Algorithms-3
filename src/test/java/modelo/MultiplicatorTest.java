package modelo;

import consumables.Multiplicator;
import org.junit.Assert;
import org.junit.Test;


public class MultiplicatorTest {

    @Test
    public void testx1MultiplicatorDontModifyPointsWhenActvated() {
        //Given
        Points points = new Points();
        points.gainAPoint();
        Multiplicator x1Multiplicator = new Multiplicator();
        Integer expectedPoints = 1;

        //When
        x1Multiplicator.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testx2MultiplicatorDoublePointsWhenActvated() {
        //Given
        Points points = new Points();
        points.gainAPoint();
        Multiplicator x2Multiplicator = new Multiplicator();
        x2Multiplicator.setFactor(2);
        Integer expectedPoints = 2;

        //When
        x2Multiplicator.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }


    @Test
    public void MultiplicatorTurnPointsTo0WhenDeactivated() {
        //Given
        Points points = new Points();
        points.gainAPoint();
        Multiplicator x1Multiplicator = new Multiplicator();
        x1Multiplicator.deactivate();
        Integer expectedPoints = 0;

        //When
        x1Multiplicator.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
}
