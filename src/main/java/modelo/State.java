package modelo;

abstract class State {
    protected Integer points;


    public void scorePlayer(ScoreBehavior score, Player player) {

    }

    public abstract String getCorrectAnswer(String text);

}
