package modelo;

public class IncorrectState extends State {

    public IncorrectState(){
        super();
    }

    public IncorrectState(Integer penalty) {
        super();
        this.points = penalty;
    }

    public void changeScore(ScoreBehavior score){
        score.punish();
    }

    public String getCorrectAnswer(String text) {
        return null;
    }
}
