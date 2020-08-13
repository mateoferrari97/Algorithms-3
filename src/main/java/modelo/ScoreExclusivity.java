package modelo;

public class ScoreExclusivity implements Consumable{
    private Multiplicator multiplicator;

    public ScoreExclusivity () {
        this.multiplicator = new Multiplicator();
    }

    public void activate() {
        this.multiplicator.upgrade();
        this.multiplicator.deactivate();
    }

    public void useWithCorrectAnswer () {}

    public void useWithIncorrectAnswer () {this.multiplicator.activate();}

    public void multiplicate(Points points) {
        this.multiplicator.multiplicate(points);
    }
}