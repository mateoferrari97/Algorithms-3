package modelo.multiplicators;

import modelo.game.Points;
import modelo.game.Turn;

import java.util.List;

import static constantes.Constantes.SCORE_EXCLUSIVITY_TEXT;

public class ScoreExclusivity implements Multiplicator {
    private Integer multiplicate = 1;
    private Points lastPoints;

    @Override
    public String getText() {
        return SCORE_EXCLUSIVITY_TEXT;
    }

    @Override
    public void multiplicate(List<Turn> turns) {
        for(Turn aTurn : turns) {
            if (this.lastPoints != null) {
                if (this.lastPoints.getPointsWithoutMultiplicate().equals(aTurn.getPoints().getPointsWithoutMultiplicate())) {
                    multiplicate = 1;
                    lastPoints.multiplicate(multiplicate);
                } else if (this.lastPoints.getPointsWithoutMultiplicate() < aTurn.getPoints().getPointsWithoutMultiplicate()) {
                    this.lastPoints.multiplicate(1);
                } else {
                    multiplicate = 1;
                }
            }
            aTurn.getPoints().multiplicate(multiplicate);
            this.lastPoints = aTurn.getPoints();
        }
    }

    @Override
    public void activate(Turn turn) {
        multiplicate = multiplicate * 2;
    }
}
