package modelo.multiplicators;

import modelo.game.Player;
import modelo.game.Points;
import modelo.game.Round;

public interface Multiplicator {

    String getText();

    void activate();

    void multiplicate(Points points);
}
