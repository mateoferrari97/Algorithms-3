package consumables;

import modelo.game.Points;

public class Multiplicator implements Consumable {
    protected Integer factor;
    protected Integer activated;

    public Multiplicator() {
        this.factor = 1;
        this.activated = 1;
    }

    public void setFactor(Integer factor) {this.factor = factor;}

    public void multiplicate(Points points) {
        points.multiplicate(this.factor * this.activated);
    }

    public void deactivate() {this.activated= 0;}

    public  void activate() {this.activated = 1;}

    public void upgrade() {this.factor = this.factor * 2;}

    public void useWithCorrectAnswer() {}

    public void useWithIncorrectAnswer() {}
}