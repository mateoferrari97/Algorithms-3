package modelo.game;

public class Loser implements State{
    @Override
    public boolean isWinner() {
        return false;
    }

    @Override
    public boolean isTied() {
        return false;
    }
}
