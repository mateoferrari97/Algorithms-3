package modelo;

public class CorrectScoreBehavior implements IScoreBehavior {
    public void score(Player player) {
        player.increasePoints();
    }
}
