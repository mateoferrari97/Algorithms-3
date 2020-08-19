package modelo.multiplicators;

import modelo.game.Points;

public class Multiplicate implements Multiplicator {
    private String text;
    private Integer multiplicate;

    public Multiplicate(String text, Integer i) {
        this.text = text;
        this.multiplicate = i;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void multiplicate(Points points) {
        points.multiplicate(this.multiplicate);
    }
}
