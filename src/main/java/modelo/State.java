package modelo;

abstract class State {

    protected State() {
    }

    public abstract void scorePlayer(ScoreBehavior score, Player player);

}
