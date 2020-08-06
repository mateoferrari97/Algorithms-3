package modelo;

public class PenaltyMultiplicator implements Multiplicator {

    public void multiplicate(Points points, Integer factor) {
        points.multiplicate(factor);
    }
}
