package modelo;

public class ScoreBehavior { // por ahora es concreta pero seria mejor que dependiendo la question se inicialize un ScoreBehavior correspondiente
    private  Integer score;
    public ScoreBehavior(){
        score = 1;
    }
    public void scorePlayer(Player player){
        player.gainAmountOfPoints(score); // esto debe estar en una clase concreta de ScoreBehavior
    }

    public void penalizePlayer(Player player) {
        // por ahora no hace nada, pero este metodo va a depender de un ScoreBehavior concreto que decide lo que va a pasar.
    }
}
