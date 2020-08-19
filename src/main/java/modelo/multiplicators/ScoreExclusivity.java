package modelo.multiplicators;

import modelo.game.Player;
import modelo.game.Points;
import modelo.game.Round;
import modelo.game.Turn;

import java.util.List;

public class ScoreExclusivity implements Multiplicator {
    private Integer multiplicate = 1;
    private Points lastPoints;

    @Override
    public String getText() {
        return "ScoreExclusivity";
    }

    @Override
    public void multiplicate(Points points) {
        if(this.lastPoints != null){
            if(this.lastPoints.getPointsWithoutMultiplicate() == points.getPointsWithoutMultiplicate()){
                multiplicate = 1;
                lastPoints.multiplicate(multiplicate);
            }
            else if(this.lastPoints.getPointsWithoutMultiplicate() < points.getPointsWithoutMultiplicate()){
                this.lastPoints.multiplicate(1);
            }
            else{
                multiplicate = 1;
            }
        }
        points.multiplicate(multiplicate);
        this.lastPoints = points;

    }

    @Override
    public void activate() {
        multiplicate = multiplicate * 2;
    }
}
