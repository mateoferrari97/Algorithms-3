package modelo.game;

import modelo.multiplicators.Multiplicate;
import modelo.multiplicators.Multiplicator;
import modelo.multiplicators.ScoreExclusivity;
import modelo.questions.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Round {
    private Integer currentTurn = 0;
    private List<Turn> turns = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Question question;
    private Set<Multiplicator> multiplcators = new HashSet<>();

    public Round(Player[] players, Question question) {
        this.question = question;
        for(Player player : players){
            this.players.add(player);
            this.turns.add(new Turn(player, question));
        }
        currentTurn = 0;
    }

    public void finish(){
        if(currentTurn == turns.size()) {
            for(Multiplicator aMultiplicator :multiplcators){
                aMultiplicator.multiplicate(turns);
            }

            for(Turn aTurn : turns){
                aTurn.givePointsToPlayers();
            }
        }
    }

    public List<Multiplicator> getMultiplicator() {
        List<Multiplicator> multiplicators = this.question.getMultiplicators();
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
        multiplcators.add(multiplicator);
        multiplicator.activate(turn);
    }

}
