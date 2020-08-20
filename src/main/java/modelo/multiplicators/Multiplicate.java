package modelo.multiplicators;

import modelo.game.Points;
import modelo.game.Round;
import modelo.game.Turn;

import java.util.List;

public class Multiplicate implements Multiplicator {
    private String text;
    private Integer multiplicate;
    private Turn turn;

    public Multiplicate(String text, Integer i) {
        this.text = text;
        this.multiplicate = i;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void activate(Turn turn) {
        this.turn = turn;
    }

    @Override
    public void multiplicate(List<Turn> turns) {
        this.turn.getPoints().multiplicate(this.multiplicate);
    }
}
