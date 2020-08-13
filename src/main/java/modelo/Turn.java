package modelo;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private Player player;
    private Question question;
    private List<Option> answers = new ArrayList<>();

    public Turn(Player aPlayer, Question question) {
        this.question = question;
        this.player = aPlayer;
    }

    public void finish() {
        question.score(player, answers);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void addPlayerAnswer(Option option) {
        answers.add(option);
    }



    public List<Option> getAnswers() {
        return this.answers;
    }
}
