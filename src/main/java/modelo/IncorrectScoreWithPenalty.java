package modelo;

public class IncorrectScoreWithPenalty implements IScoreBehavior{
    public void score(Player player, Integer points) {
        player.decreasePoints(points);
    }
}
