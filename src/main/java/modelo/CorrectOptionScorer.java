package modelo;

public class CorrectOptionScorer implements IOptionScorer {
    public void score(Player player) {
        player.increasePoints();
    }
}
