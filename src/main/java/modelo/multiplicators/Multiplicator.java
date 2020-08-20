package modelo.multiplicators;

import modelo.game.Points;
import modelo.game.Round;
import modelo.game.Turn;

import java.util.List;

public interface Multiplicator {

    String getText();

    void activate(Turn turn);

    void multiplicate(List<Turn> turns);
}
