package modelo.game;


import modelo.multiplicators.ScoreExclusivity;
import modelo.options.Option;
import modelo.multiplicators.Multiplicator;
import modelo.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private Points points;
    private Player player;
    private Question question;
    private List<Option> answers = new ArrayList<>();
    private Integer amountOfCurrentOptions;
    private List<Multiplicator> multiplicators;

    public Turn(Player aPlayer, Question question) {
        this.question = question;
        this.player = aPlayer;
        this.amountOfCurrentOptions = 0;
        this.multiplicators = question.getMultiplicators();
        this.points = new Points();
    }

    public void finish() {
        question.selectOptions(answers, this.points);

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

    public Integer getAmountCurrentOptions() { // ESTA HACIENDO LO MISMO QUE getAmountOfOptions
        return this.amountOfCurrentOptions;
    }

    public List<Multiplicator> getMultiplicator() {
        return this.multiplicators;
    }

    public void multiplicate(Multiplicator multiplicator) {
        question.multiplicate(multiplicator);
    }

    public Points getPoints() {
        return this.points;
    }
}
