package modelo.game;

import modelo.multiplicators.Multiplicator;
import modelo.multiplicators.ScoreExclusivity;
import modelo.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private Integer currentTurn = 0;
    private List<Turn> turns = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Question question;
    private Multiplicator multiplicator;

    public Round(Player[] players, Question question) {
        this.question = question;

        for(Player player : players){
            this.players.add(player);
            this.turns.add(new Turn(player, question));
        }
    }

    public void finish(){
        if(currentTurn == turns.size()) {
            if(multiplicator == null){
                for(Turn aTurn : turns){
                    if(aTurn.getUsedMultiplicator() != null) {
                        aTurn.multiplicatePoints();
                    }
                }
            }
            else{
                for(Turn aTurn : turns){
                    multiplicator.multiplicate(aTurn.getPoints());
                }
            }
            for(Turn aTurn : turns){
                aTurn.getPoints().givePointsToPlayer(aTurn.getPlayer());
            }
        }
    }

    public List<Multiplicator> getMultiplicator() {
        List<Multiplicator> multiplicators = this.question.getMultiplicators();
        if(multiplicators.size() == 1){
            this.multiplicator = multiplicators.get(0);
        }
        return multiplicators;
    }

    public Turn getTurn(Game game) {
        if(currentTurn == turns.size() - 1){
            game.setNextRound();
            currentTurn++;
            return turns.get(currentTurn - 1);
        }
        Turn turn = turns.get(currentTurn);
        currentTurn++;
        return turn;
    }

    public Question getQuestion() {
        return this.question;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void multiplicate(Multiplicator multiplicator, Turn turn) {
        if(this.multiplicator != null){
            this.multiplicator.activate();
        }
        else {
            turn.multiplicate(multiplicator);
        }
    }
}
