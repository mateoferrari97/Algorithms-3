package modelo;

import modelo.options.Option;
import modelo.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private Player player;
    private Question question;
    private List<Option> answers = new ArrayList<>();
    private Integer amountOfCurrentOptions;

    public Turn(Player aPlayer, Question question) {
        this.question = question;
        this.player = aPlayer;
        this.amountOfCurrentOptions = 0;
    }

    public void finish() {
        question.selectOptions(answers);
        question.score(player);
    }

    public Player getPlayer() {
        return this.player;
    }


    public void addPlayerAnswer(Option option) {
        this.amountOfCurrentOptions++;
        answers.add(option);
    }

    public  void increaseAmountOfCurrentOptions(){this.amountOfCurrentOptions = this.amountOfCurrentOptions + 1;}

    public List<Option> getAnswers() {
        return this.answers;
    }

    public Integer getAmountChosenOptions() {
        return this.question.getCorrectOptions().size();
    }

    public Integer getAmountOfOptions () {
        return this.question.getOptions().size();
    }

    public Integer getAmountCurrentOptions() {
        return this.amountOfCurrentOptions;
    }
}
