package modelo.game;

import modelo.multiplicators.ScoreExclusivity;
import modelo.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private Integer currentTurn = 0;
    private List<Turn> turns = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Question question;

    public Round(Player[] players, Question question) {
        this.question = question;

        for(Player player : players){
            this.players.add(player);
            this.turns.add(new Turn(player, question));
        }
    }

    public void finish(){
        if(currentTurn == turns.size()) {
            for(Turn aTurn : turns){
                if(aTurn.getUsedMultiplicator() != null){
                    aTurn.getUsedMultiplicator().multiplicate(aTurn.getPoints());
                }
            }
            for(Turn aTurn : turns){
                aTurn.getPoints().givePointsToPlayer(aTurn.getPlayer());
            }
        }
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
}
