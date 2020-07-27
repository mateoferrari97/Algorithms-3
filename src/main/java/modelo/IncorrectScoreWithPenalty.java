package modelo;

public class IncorrectScoreWithPenalty implements IScoreBehavior{
    public void score(Player player) {
        player.decreasePoints();
    }
}
