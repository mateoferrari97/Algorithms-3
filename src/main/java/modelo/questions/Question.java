package modelo.questions;


import modelo.game.Points;
import modelo.game.Round;
import modelo.multiplicators.Multiplicator;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.LinkedList;
import java.util.List;

public abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected String type;
    protected List<Multiplicator> multiplicator;

    public abstract void selectOptions(List<Option> playerAnswers, Points points);

    public String getText() {
        return this.text;
    }

    public String[] getAnswerOptions() {
        int i = 0;
        String[] answersOptions = new String[this.options.size()];
        for (Option aOption : options) {
            answersOptions[i] = aOption.getText();
            i++;
        }
        return answersOptions;
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public List<Option> getCorrectOptions() {
        List<Option> options = new LinkedList<>();
        for (Option option : this.options) {
            if (option.isCorrect()) {
                options.add(option);
            }
        }

        return options;
    }

    public String getType() {
        return this.type;
    }

    public List<Multiplicator> getMultiplicators() {
        return this.multiplicator;
    }

}




