package modelo.game;

public class Tied implements State{
    @Override
    public boolean isWinner() {
        return false;
    }

    @Override
    public boolean isTied() {
        return true;
    }
}
