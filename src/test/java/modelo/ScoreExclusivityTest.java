package modelo;

import org.junit.Assert;
import org.junit.Test;


public class ScoreExclusivityTest {

    @Test
    public void testScoreExclusivityDontModifyPointsWhenNotActivatedAndCorrectAnswers(){
        //Given
        Integer expectedPoints = 1;
        Points points  = new Points();
        points.gainAPoint();

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        scoreExclusivity.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(),expectedPoints);

    }

    @Test
    public void testScoreExclusivityDontModifyPointsWhenNotActivatedAndOnePlayerAnswerIncorrectly(){
        //Given
        Integer expectedPoints = 1;
        Points points  = new Points();
        points.gainAPoint();

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        scoreExclusivity.useWithCorrectAnswer();
        scoreExclusivity.useWithCorrectAnswer();
        scoreExclusivity.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(),expectedPoints);

    }

    @Test
    public void testScoreExclusivityDoublePointsWhenActivatedAndOnePlayerAnswerIncorrectly(){
        //Given
        Integer expectedPoints = 2;
        Points points  = new Points();
        points.gainAPoint();

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        scoreExclusivity.activate();
        scoreExclusivity.useWithIncorrectAnswer();
        scoreExclusivity.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(),expectedPoints);
    }

    @Test
    public void testScoreExclusivityQuadruplePointsWhenTwoTimesActivatedAndOnePlayerAnswerIncorrectly(){
        //Given
        Integer expectedPoints = 4;
        Points points  = new Points();
        points.gainAPoint();

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        scoreExclusivity.activate();
        scoreExclusivity.activate();
        scoreExclusivity.useWithIncorrectAnswer();

        scoreExclusivity.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(),expectedPoints);
    }

    @Test
    public void testScoreExclusivityTurnPointsToZeroWhenActivatedAndNoWrongAnswers(){
        //Given
        Integer expectedPoints = 0;
        Points points  = new Points();
        points.gainAPoint();

        ScoreExclusivity scoreExclusivity = new ScoreExclusivity();

        //When
        scoreExclusivity.activate();
        scoreExclusivity.multiplicate(points);

        //Then
        Assert.assertEquals(points.getPoints(),expectedPoints);
    }
}
