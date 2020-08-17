package modelo.consumables;

import modelo.game.Points;

public class ScoreExclusivity implements Consumable {
    private Multiplicator multiplicator;

    public ScoreExclusivity () {
        this.multiplicator = new Multiplicator();
        this.multiplicator.deactivate();
    }

    public void activate() {
        this.multiplicator.upgrade();
    }

    public void useWithCorrectAnswer () {}

    public void useWithIncorrectAnswer () {this.multiplicator.activate();}

    public void multiplicate(Points points) {
        this.multiplicator.multiplicate(points);
    }
}