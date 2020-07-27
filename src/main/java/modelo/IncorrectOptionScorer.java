package modelo;

public class IncorrectOptionScorer implements IOptionScorer {
    public void score(Player player) {
        player.decreasePoints();
    }
}
