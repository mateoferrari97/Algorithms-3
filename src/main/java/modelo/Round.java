package modelo;

public class Round {
    private Turn[] turns = new Turn[2];
    private Player[] players = new Player[2];
    private Question question;

    public Round(Player[] players, Question aQuestion) {
        int i = 0;
        this.question = aQuestion;
        this.players = players;
        for(Player aPlayer : players){
           turns[i] = new Turn(aPlayer);
        }
    }

    public void start() {
        for(Turn aTurn : turns){
            aTurn.start();
        }
    }
}
