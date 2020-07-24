package modelo;

public class CorrectOption extends State {
    public CorrectOption() {
        super();
    }

    public void scorePlayer(ScoreBehavior score, Player player){
        score.scorePlayer(player);
    }
}
