package modelo;

abstract class State {
    protected Integer points;

    public void setPoints(Integer points){
        this.points = points;
    }

    public abstract String getCorrectAnswer(String text);

    public abstract void changeScore(ScoreBehavior score);

}
