package modelo.multiplicators;

import modelo.game.Points;
import modelo.game.Round;
import modelo.game.Turn;

import java.util.List;

public class ScoreExclusivity implements Multiplicator {
    private Integer multiplicate = 1;
    private Integer lastPoints;
    private Turn lastTurn;

    @Override
    public String getText() {
        return "ScoreExclusivity";
    }

    @Override
    public void multiplicate(List<Turn> turns) {
        this.lastTurn = turns.get(0);
        for(Turn aTurn : turns) {
            if (this.lastPoints != null) {
                if (this.lastPoints == aTurn.getPoints().getPointsWithoutMultiplicate()) {
                    multiplicate = 1;
                    lastTurn.getPoints().multiplicate(multiplicate);
                } else if (this.lastPoints < aTurn.getPoints().getPointsWithoutMultiplicate()) {
                    this.lastTurn.getPoints().multiplicate(1);
                } else {
                    multiplicate = 1;
                }
            }
            aTurn.getPoints().multiplicate(multiplicate);
            this.lastPoints = aTurn.getPoints().getPointsWithoutMultiplicate();
        }
    }

    @Override
    public void activate(Turn turn) {
        multiplicate = multiplicate * 2;
    }
}
