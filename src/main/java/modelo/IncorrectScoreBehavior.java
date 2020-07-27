package modelo;

public class IncorrectScoreBehavior implements IScoreBehavior {
    public void score(Player player, Integer points) {
        points = 0;
        player.increasePoints(points);
    }
}
