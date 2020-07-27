package modelo;

public class MultipleChoiceClassicSB extends ScoreBehavior {
    private Integer points;
    private Integer factor;


    public MultipleChoiceClassicSB(){
        super();
        this.points = 0;
        this.factor = 1;
    }

    public void reward(Integer points){
        this.points = points;
    }

    public void punish(){
        this.factor = 0;
    }

    public void scorePlayer(Player player){
        player.gainAmountOfPoints(this.points * this.factor);
    }
}
