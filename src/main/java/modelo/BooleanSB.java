package modelo;

public class BooleanSB extends ScoreBehavior {
    private Integer points;

    public BooleanSB(){
        this.points = 0;
    }

    void scorePlayer(Player player) {
        player.gainAmountOfPoints(this.points);
        this.points = 0;
    }

    void reward(Integer points) {
        this.points = points;
    }

    void punish() {

    }
}
