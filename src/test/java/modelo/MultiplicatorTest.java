package modelo;

import exceptions.InvalidSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class MultiplicatorTest {

    @Test
    public void testx1MultiplicatorDontModifyPointsWhenActvated() {
        //Given
        Points points = new Points();
        points.gainAPoint();
        x1Multiplicator x1Multiplicator = new x1Multiplicator();
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
        x2Multiplicator x2Multiplicator = new x2Multiplicator();
        Integer expectedPoints = 2;

        //When
        x2Multiplicator.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void testx3MultiplicatorTriplePointsWhenActvated() {
        //Given
        Points points = new Points();
        points.gainAPoint();
        x3Multiplicator x3Multiplicator = new x3Multiplicator();
        Integer expectedPoints = 3;

        //When
        x3Multiplicator.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }

    @Test
    public void MultiplicatorTurnPointsTo0WhenDeactivated() {
        //Given
        Points points = new Points();
        points.gainAPoint();
        x1Multiplicator x1Multiplicator = new x1Multiplicator();
        x1Multiplicator.deactivate();
        Integer expectedPoints = 0;

        //When
        x1Multiplicator.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(), expectedPoints);
    }
}
