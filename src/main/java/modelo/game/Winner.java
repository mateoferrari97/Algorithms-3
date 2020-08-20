package modelo.game;

public class Winner implements State{
    @Override
    public boolean isWinner() {
        return true;
    }

    @Override
    public boolean isTied() {
        return false;
    }
}
