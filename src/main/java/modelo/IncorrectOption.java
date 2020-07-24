package modelo;

public class IncorrectOption extends State {

    public IncorrectOption() {
        super();
    }

    public void scorePlayer(ScoreBehavior score, Player player){
        score.penalizePlayer(player);
    }
}
