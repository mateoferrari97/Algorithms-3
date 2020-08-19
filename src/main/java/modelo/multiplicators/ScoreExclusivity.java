package modelo.multiplicators;

import modelo.game.Points;

public class ScoreExclusivity implements Multiplicator {
    private Integer timeUsed = 0;
    private Integer normalMultiplication = 2;
    private Integer enhancedMultiplication = normalMultiplication * 2;

    @Override
    public String getText() {
        return "ScoreExclusivity";
    }

    @Override
    public void multiplicate(Points points) {
        if(timeUsed >= 1){
            points.multiplicate(enhancedMultiplication);
        }
        else{
            points.multiplicate(normalMultiplication);
        }
        timeUsed++;
    }

}
