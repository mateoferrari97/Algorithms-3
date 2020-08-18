package modelo.multiplicators;

import modelo.game.Points;

public interface Multiplicator {

    String getText();

    void multiplicate(Points points);

    Multiplicator getScoreExclusivity();

}
