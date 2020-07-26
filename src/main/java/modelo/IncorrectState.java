package modelo;

public class IncorrectState extends State {

    public IncorrectState() {
        super();
        this.points = 0;
    }

    public void scorePlayer(ScoreBehavior score, Player player){
        score.scorePlayer(player, this.points);
    }

    public String getCorrectAnswer(String text) {
        return null;
    }

    public void setPenalty(Integer penalty) {
        this.points = penalty;
    }
}
