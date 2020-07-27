package modelo;

public class CorrectState extends State {

    public CorrectState(Integer points) {
       super();
       this.points = points;
    }

    public void changeScore(ScoreBehavior score){
        score.reward(this.points);
    }

    public String getCorrectAnswer(String text) {
        return text;
    }

}
